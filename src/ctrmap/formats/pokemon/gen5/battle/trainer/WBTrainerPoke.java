
package ctrmap.formats.pokemon.gen5.battle.trainer;

import java.io.DataInput;
import java.io.IOException;
import xstandard.io.base.impl.ext.data.DataIOStream;

public class WBTrainerPoke {
    final int MOVES_COUNT = 0x4;
    Integer[] Moves;
    int IVs, Ability, Gender, Level, Species, Form, Item;
    
    public WBTrainerPoke() {
        Moves = new Integer[]{0, 0, 0, 0} ;
    }
    
    public WBTrainerPoke(DataInput in, boolean CanOverrideMoves, boolean CanOverrideItem) throws IOException {
        this();
        IVs = in.readUnsignedByte(); 
        int PID = in.readUnsignedByte();
        Ability = (PID >>> 0x4) & 0b1111;
        Gender = PID & 0b1111;
        Level = in.readUnsignedShort();
        Species = in.readUnsignedShort();
        Form = in.readUnsignedShort();
        Item = CanOverrideItem ? in.readUnsignedShort() : 0;
        Moves = new Integer[MOVES_COUNT];
        for (int Index = 0; Index < MOVES_COUNT; ++Index) {
            Moves[Index] = CanOverrideMoves ? in.readUnsignedShort() : 0;
        }
    }
    
    public int GetIV() {
        return this.IVs;
    }
    
    public void SetIV(int IVs) {
        this.IVs = IVs;
    }
    
    public int GetAbility() {
        return this.Ability;
    }
    
    public void SetAbility(int Ability) {
        this.Ability = Ability;
    }
    
    public int GetGender() {
        return this.Gender;
    }
    
    public void SetGender(int Gender) {
        this.Gender = Gender;
    }
    
    public int GetLevel() {
        return this.Level;
    }
    
    public void SetLevel(int Level) {
        this.Level = Level;
    }
    
    public int GetSpecies() {
        return this.Species;
    }
    
    public void SetSpecies(int Species) {
        this.Species = Species;
    }

    public int GetForm() {
        return this.Form;
    }
    
    public void SetForm(int Form) {
        this.Form = Form;
    }
    
    
    public int GetHeldItem() {
        return this.Item;
    }
    
    public void SetHeldItem(int Item) {
        this.Item = Item;
    }
    
    public int GetMove(int Index) {
        if (Index < 0 || Index >= MOVES_COUNT) {
            return -1;
        }
        return this.Moves[Index];
    }
    
    public void SetMove(int Index, int NewMove) {
        if (Index < 0 || Index >= MOVES_COUNT) {
            return;
        }
        this.Moves[Index] = NewMove;
    }

    void Serialize(DataIOStream TrPokeStrm, boolean OverrideItem, boolean OverrideMoves) throws IOException {
        TrPokeStrm.writeByte(this.IVs);
        TrPokeStrm.writeByte((this.Ability << 0x4) | (this.Gender));
        TrPokeStrm.writeShort(Level);
        TrPokeStrm.writeShort(Species);
        TrPokeStrm.writeShort(Form);
        if (OverrideItem) {
            TrPokeStrm.writeShort(this.Item);
        }
        if (OverrideMoves) {
            for (int Index = 0; Index < MOVES_COUNT; ++Index) {
                TrPokeStrm.writeShort(this.Moves[Index]);
            }
        }
    }
}
