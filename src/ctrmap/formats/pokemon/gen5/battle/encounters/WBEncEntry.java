/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ctrmap.formats.pokemon.gen5.battle.encounters;

import java.io.DataInput;
import java.io.IOException;

public class WBEncEntry {
    short DexNum, FormNum;
    byte MinLevel, MaxLevel;
    
    public WBEncEntry() {
        
    }
    
    public WBEncEntry(DataInput Input) throws IOException {
        short IDForm = Input.readShort();
        DexNum = (short) (IDForm & 0x7FF);
        FormNum = (short) (IDForm >>> 0xB);
        MinLevel = Input.readByte();
        MaxLevel = Input.readByte();
    }
    
    public short GetDexNum() {
        return this.DexNum;
    }
    
    public void SetDexNum(short DexNum) {
        this.DexNum = DexNum;
    }
        
    public short GetFormNum() {
        return this.FormNum;
    }
    
    public void SetFormNum(short FormNum) {
        this.FormNum = FormNum;
    }
    
    public short GetMinLevel() {
        return this.MinLevel;
    }
    
    public void SetMinLevel(byte MinLevel) {
        this.MinLevel = MinLevel;
    }
    
    public short GetMaxLevel() {
        return this.MinLevel;
    }
    
    public void SetMaxLevel(byte MinLevel) {
        this.MaxLevel = MaxLevel;
    }
}
