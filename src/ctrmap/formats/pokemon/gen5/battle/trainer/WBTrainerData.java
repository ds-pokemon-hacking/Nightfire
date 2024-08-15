
package ctrmap.formats.pokemon.gen5.battle.trainer;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import xstandard.fs.FSFile;
import xstandard.io.base.impl.ext.data.DataIOStream;

public class WBTrainerData {
    public static final short ITEMS_COUNT_MAX = 0x4;
    Boolean[] AI;
    Integer[] Items;
    ArrayList<WBTrainerPoke> TrPoke;
    
    boolean OverrideMoves, OverrideHeldItem, CanHeal;
    int AssignedClass, BattleType, Money, Prize;
    
    public WBTrainerData() {
        AI = new Boolean[Integer.SIZE];
        Items = new Integer[ITEMS_COUNT_MAX];
        TrPoke = new ArrayList<>();
    }
    
    public WBTrainerData(DataInput in, DataInput TrPkmn) throws IOException {
        this();
        
        long Format = in.readUnsignedByte();
        this.OverrideHeldItem = ((Format >>> 0x1) & 0x1) == 1;
        this.OverrideMoves = (Format & 0x1) == 1;
        
        this.AssignedClass = in.readUnsignedByte();
        this.BattleType = in.readUnsignedByte();
        
        int PkmnCnt = in.readUnsignedByte();
        
        for (int Index = 0; Index < ITEMS_COUNT_MAX; ++Index) {
            this.Items[Index] = in.readUnsignedShort();
        }

        SetAIValue(in.readInt());
        
        if (PkmnCnt > 0) {
            this.CanHeal = in.readBoolean();
            this.Money = in.readUnsignedByte();
            this.Prize = in.readUnsignedShort();
            for (int Index = 0; Index < PkmnCnt; ++Index) {
                TrPoke.add(new WBTrainerPoke(TrPkmn, OverrideMoves, OverrideHeldItem));
            }
        }
    }
    
    public int GetAssignedClass() {
        return AssignedClass;
    }
    
    public void SetAssignedClass(int AssignedClass) {
        this.AssignedClass = AssignedClass;
    }
    
    public int GetBattleType() {
        return this.BattleType;
    }
    
    public void SetBattleType(int BattleType) {
        this.BattleType = BattleType;
    }
    
    public int GetMoneyIndex() {
        return this.Money;
    }
    
    public void SetMoneyIndex(int Money) {
        this.Money = Money;
    }
    
    public int GetPrizeIndex() {
        return this.Prize;
    }
    
    public void SetPrizeIndex(int Prize) {
        this.Prize = Prize;
    }
    
    public boolean CanOverrideMoves() {
        return this.OverrideMoves;
    }
    
    public void SetCanOverrideMoves(boolean CanOverride) {
        this.OverrideMoves = CanOverride;
    }
    
    public boolean CanOverrideHeldItem() {
        return this.OverrideHeldItem;
    }
    
    public void SetCanOverrideHeldItem(boolean CanOverride) {
        this.OverrideHeldItem = CanOverride;
    }
    
    public boolean GetCanHeal() {
        return this.CanHeal;
    }
    
    public void SetCanHeal(boolean CanOverride) {
        this.CanHeal = CanOverride;
    }
    
    public int GetItem(int SlotIndex) {
        return SlotIndex >= 0 && SlotIndex < ITEMS_COUNT_MAX ? Items[SlotIndex] : -1;
    }
    
    public void SetItem(int SlotIndex, int Item) {
        if (SlotIndex >= 0 && SlotIndex < ITEMS_COUNT_MAX) {
            Items[SlotIndex] = Item;
        }
    }
    
    public boolean GetAIFlag(int FlagIndex) {
        return FlagIndex >= 0 && FlagIndex < Integer.SIZE ? AI[FlagIndex] : false;
    }
    
    public void SetAIFlag(int FlagIndex, boolean Value) {
        if (FlagIndex >= 0 && FlagIndex < Integer.SIZE) {
            AI[FlagIndex] = Value;
        }
    }

    public int GetAIValue() {
        long RetVal = 0;
        for (int Index = 0; Index < Integer.SIZE; ++Index) {
            RetVal |= (AI[Index] ? 1 : 0) << Index;
        }
        return (int) RetVal;
    }
    
    public void SetAIValue(int Value) {
        int Sh = Value;
        for (int Index = 0; Sh > 0 || Index < Integer.SIZE; ++Index, Sh >>>= 1) {
            this.AI[Index] = (Sh & 1) == 1;
        }
    }
    
    public int GetPkmnSize() {
        return this.TrPoke.size();
    }
    
    public WBTrainerPoke GetPkmn(int Index) {
        return Index < GetPkmnSize() ? this.TrPoke.get(Index) : null;
    }
    
    public void Serialize(FSFile TrDat, FSFile TrPoke) {
        try {			
            DataIOStream TrDatStrm = TrDat.getDataIOStream();

            TrDatStrm.writeByte(((this.OverrideHeldItem ? 1 : 0) << 1) | (this.OverrideMoves ? 1 : 0));
            TrDatStrm.writeByte(this.AssignedClass);
            TrDatStrm.writeByte(this.BattleType);
            
            TrDatStrm.writeByte(this.GetPkmnSize());

            for (int Index = 0; Index < ITEMS_COUNT_MAX; ++Index) {
                TrDatStrm.writeShort(this.Items[Index]);
            }

            TrDatStrm.writeInt(this.GetAIValue());

            if (this.GetPkmnSize() > 0) {
                DataIOStream TrPokeStrm = TrPoke.getDataIOStream();
                TrDatStrm.writeByte(this.CanHeal ? 1 : 0);
                TrDatStrm.writeByte(this.Money);
                TrDatStrm.writeByte(this.Prize); 
                for (WBTrainerPoke Entry : this.TrPoke) {
                    Entry.Serialize(TrPokeStrm, this.CanOverrideHeldItem(), this.CanOverrideMoves());
                }
                TrPokeStrm.close();
            }

            TrDatStrm.close();
        } catch (IOException ex) {
        }
    }
}
