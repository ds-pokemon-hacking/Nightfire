package ctrmap.formats.pokemon.gen5.battle.encounters;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;

public class WBEnc {
    public static enum WBEncType {
            GRASS,
            GRASS_DOUBLE,
            GRASS_RARE,
            SURF,
            SURF_RARE,
            FISH,
            FISH_RARE;
            public static final WBEncType[] VALUES = values();
    }
    ArrayList<WBEncEntry> GrassEnc, GrassDoubleEnc, GrassRareEnc;
    ArrayList<WBEncEntry> SurfEnc, SurfRareEnc;
    ArrayList<WBEncEntry> FishEnc, FishRareEnc;
    byte[] UserDataProps;
    byte FishEncProb, FishEncProb2, Flags;
    public static final int GRASS_ENC_SLOTS = 0xC, SURF_ENC_SLOTS = 0x5, FISH_ENC_SLOTS = 0x5;
    
    public WBEnc() {
        UserDataProps = new byte[] {0, 0, 0, 0, 0};
        FishEncProb = 0;
        FishEncProb2 = 0;
        Flags = 0;
        // Realistically, it should be 
        GrassEnc = new ArrayList<>();
        GrassDoubleEnc = new ArrayList<>();
        GrassRareEnc = new ArrayList<>();
        SurfEnc = new ArrayList<>();
        SurfRareEnc = new ArrayList<>();
        FishEnc = new ArrayList<>();
        FishRareEnc = new ArrayList<>();
    }
    
    public WBEnc(DataInput Input) throws IOException {
        this();
        System.out.println("Encounter test...");
        // Header
        for (int Index = 0; Index < 0x5; ++Index) {     
            this.UserDataProps[Index] = Input.readByte();
        }
        this.FishEncProb = Input.readByte();
        this.FishEncProb2 = Input.readByte();
        this.Flags = Input.readByte();
        // Grass
        for (int Index = 0; Index < GRASS_ENC_SLOTS; ++Index) {
           this.GrassEnc.add(new WBEncEntry(Input));
        }
        for (int Index = 0; Index < GRASS_ENC_SLOTS; ++Index) {
           this.GrassDoubleEnc.add(new WBEncEntry(Input));
        }
        for (int Index = 0; Index < GRASS_ENC_SLOTS; ++Index) {
           this.GrassRareEnc.add(new WBEncEntry(Input));
        }
        // Surf
        for (int Index = 0; Index < SURF_ENC_SLOTS; ++Index) {
           this.SurfEnc.add(new WBEncEntry(Input));
        }
        for (int Index = 0; Index < SURF_ENC_SLOTS; ++Index) {
           this.SurfRareEnc.add(new WBEncEntry(Input));
        }
        // Fish
        for (int Index = 0; Index < FISH_ENC_SLOTS; ++Index) {
           this.FishEnc.add(new WBEncEntry(Input));
        }
        for (int Index = 0; Index < FISH_ENC_SLOTS; ++Index) {
           this.FishRareEnc.add(new WBEncEntry(Input));
        }
    }
    
    public WBEncEntry GetGrassEncounter(WBEncType Type, int Slot) {
        switch (Type) {
            case GRASS:
                return Slot < Math.min(GRASS_ENC_SLOTS, GrassEnc.size()) ? GrassEnc.get(Slot) : null;
            case GRASS_DOUBLE:
                return Slot < Math.min(GRASS_ENC_SLOTS, GrassDoubleEnc.size()) ? GrassDoubleEnc.get(Slot) : null;
            case GRASS_RARE:
                return Slot < Math.min(GRASS_ENC_SLOTS, GrassRareEnc.size()) ? GrassRareEnc.get(Slot) : null;
            default:
                return null;
        }
    }
    
    public WBEncEntry GetSurfEncounter(WBEncType Type, int Slot) {
        switch (Type) {
            case SURF:
                return Slot < SurfEnc.size() ? SurfEnc.get(Slot) : null;
            case SURF_RARE:
                return Slot < SurfRareEnc.size() ? SurfRareEnc.get(Slot) : null;
            default:
                return null;
        }
    }
    
    public WBEncEntry GetFishEncounter(WBEncType Type, int Slot) {
        switch (Type) {
            case FISH:
                return Slot < FishEnc.size() ? FishEnc.get(Slot) : null;
            case FISH_RARE:
                return Slot < FishRareEnc.size() ? FishRareEnc.get(Slot) : null;
            default:
                return null;
        }
    }
}
