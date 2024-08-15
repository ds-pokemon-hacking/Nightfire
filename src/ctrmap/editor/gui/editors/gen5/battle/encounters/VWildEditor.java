package ctrmap.editor.gui.editors.gen5.battle.encounters;
import ctrmap.editor.CTRMap;
import ctrmap.editor.gui.editors.common.AbstractTabbedEditor;
import ctrmap.editor.system.workspace.CTRMapProject;
import ctrmap.formats.common.GameInfo;
import ctrmap.formats.common.GameInfoListener;
import ctrmap.formats.pokemon.gen5.battle.encounters.WBEnc;
import ctrmap.formats.pokemon.text.GenVMessageHandler;
import ctrmap.formats.pokemon.text.TextFile;
import ctrmap.missioncontrol_base.debug.IMCDebugger;
import ctrmap.missioncontrol_ntr.VLaunchpad;
import ctrmap.missioncontrol_ntr.field.debug.VZoneDebugger;
import ctrmap.missioncontrol_ntr.field.structs.VZone;
import ctrmap.missioncontrol_ntr.fs.NARCRef;
import ctrmap.missioncontrol_ntr.fs.NTRGameFS;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import xstandard.fs.FSFile;

/**
 *
 * @author platinum
 */
public class VWildEditor extends javax.swing.JPanel implements VZoneDebugger, AbstractTabbedEditor {
    private CTRMap Instance;
    private TextFile PkmnNames;
    private ArrayList<WBEnc> CurrentEncounters;
    
    public VWildEditor() {
        initComponents();
    }
    
    public VWildEditor(CTRMap Instance) {
        initComponents();
        this.Instance = Instance;
        this.regGrassPanel.setLayout(new GridLayout(12, 1));
        this.doubleGrassPanel.setLayout(new GridLayout(12, 1));
        this.rareGrassPanel.setLayout(new GridLayout(12, 1));
        this.regSurfPanel.setLayout(new GridLayout(5, 1));
        this.rareSurfPanel.setLayout(new GridLayout(5, 1));
        this.regFishPanel.setLayout(new GridLayout(5, 1));
        this.rareFishPanel.setLayout(new GridLayout(5, 1));
    }
    
    NTRGameFS FS() {
        return this.Instance.getMissionControl(VLaunchpad.class).fs;
    }
    
    @Override
    public String getTabName() {
        return "Wild Encounter Editor";
    }

    @Override
    public boolean isGameSupported(GameInfo game) {
        return game.isGenV();
    }
    
    @Override
    public void loadZone(VZone z) {
        if (z != null) {
            try {
                LoadEncountersFromFile(z.header.encID);
                LoadEncounterByIndex(0);
            } catch (IOException ex) {
                Logger.getLogger(VWildEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void LoadEncountersFromFile(int Index) throws IOException {
        if (Index >= 0 && Index < FS().NARCGetDataMax(NARCRef.FIELD_ZONE_ENCOUNTERS)) {
            FSFile Encs = FS().NARCGet(NARCRef.FIELD_ZONE_ENCOUNTERS, Index);
            CurrentEncounters = new ArrayList<>(); 
            for (int EncIdx = 0; EncIdx < Encs.length() / 0xE8; ++EncIdx) {
                CurrentEncounters.add(new WBEnc(Encs.getDataIOStream()));
            }
        }
    }
    
    private void LoadEncounterByIndex(int Index) {
        this.regGrassPanel.removeAll();
        this.doubleGrassPanel.removeAll();
        this.rareGrassPanel.removeAll();
        
        if (CurrentEncounters != null && Index < CurrentEncounters.size()) {
            WBEnc Enc = this.CurrentEncounters.get(Index);
            for (int EncIdx = 0; EncIdx < WBEnc.GRASS_ENC_SLOTS; ++EncIdx) {
                this.regGrassPanel.add(new VWildEncounterSlot(Enc.GetGrassEncounter(WBEnc.WBEncType.GRASS, EncIdx), this.PkmnNames));
            }
            for (int EncIdx = 0; EncIdx < WBEnc.GRASS_ENC_SLOTS; ++EncIdx) {
                this.doubleGrassPanel.add(new VWildEncounterSlot(Enc.GetGrassEncounter(WBEnc.WBEncType.GRASS_DOUBLE, EncIdx), this.PkmnNames));
            }
            for (int EncIdx = 0; EncIdx < WBEnc.GRASS_ENC_SLOTS; ++EncIdx) {
                this.rareGrassPanel.add(new VWildEncounterSlot(Enc.GetGrassEncounter(WBEnc.WBEncType.GRASS_RARE, EncIdx), this.PkmnNames));
            }            
            for (int EncIdx = 0; EncIdx < WBEnc.FISH_ENC_SLOTS; ++EncIdx) {
                this.regFishPanel.add(new VWildEncounterSlot(Enc.GetGrassEncounter(WBEnc.WBEncType.FISH, EncIdx), this.PkmnNames));
            }  
            for (int EncIdx = 0; EncIdx < WBEnc.FISH_ENC_SLOTS; ++EncIdx) {
                this.rareFishPanel.add(new VWildEncounterSlot(Enc.GetGrassEncounter(WBEnc.WBEncType.FISH_RARE, EncIdx), this.PkmnNames));
            }            
            for (int EncIdx = 0; EncIdx < WBEnc.SURF_ENC_SLOTS; ++EncIdx) {
                this.regSurfPanel.add(new VWildEncounterSlot(Enc.GetGrassEncounter(WBEnc.WBEncType.SURF, EncIdx), this.PkmnNames));
            }  
            for (int EncIdx = 0; EncIdx < WBEnc.SURF_ENC_SLOTS; ++EncIdx) {
                this.rareSurfPanel.add(new VWildEncounterSlot(Enc.GetGrassEncounter(WBEnc.WBEncType.SURF_RARE, EncIdx), this.PkmnNames));
            }
        }
    }

    @Override
    public void onProjectLoaded(CTRMapProject proj) {
        PkmnNames = new TextFile(FS().NARCGet(NARCRef.MSGDATA_SYSTEM, 90), GenVMessageHandler.INSTANCE);
    }

    @Override
    public void onProjectUnloaded(CTRMapProject proj) {
        
    } 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        regularPanel = new javax.swing.JPanel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        regGrassPanel = new javax.swing.JPanel();
        regularPanel1 = new javax.swing.JPanel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        doubleGrassPanel = new javax.swing.JPanel();
        regularPanel2 = new javax.swing.JPanel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        rareGrassPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        regularPanel5 = new javax.swing.JPanel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        regSurfPanel = new javax.swing.JPanel();
        regularPanel6 = new javax.swing.JPanel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        rareSurfPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        regularPanel7 = new javax.swing.JPanel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jLabel167 = new javax.swing.JLabel();
        regFishPanel = new javax.swing.JPanel();
        regularPanel8 = new javax.swing.JPanel();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        rareFishPanel = new javax.swing.JPanel();

        regularPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Regular", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel128.setText("Minimum Level");

        jLabel129.setText("Maximum Level");

        jLabel130.setText("Species");

        jLabel131.setText("Form");

        jLabel143.setText("Chance");

        regGrassPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout regGrassPanelLayout = new javax.swing.GroupLayout(regGrassPanel);
        regGrassPanel.setLayout(regGrassPanelLayout);
        regGrassPanelLayout.setHorizontalGroup(
            regGrassPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        regGrassPanelLayout.setVerticalGroup(
            regGrassPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout regularPanelLayout = new javax.swing.GroupLayout(regularPanel);
        regularPanel.setLayout(regularPanelLayout);
        regularPanelLayout.setHorizontalGroup(
            regularPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regularPanelLayout.createSequentialGroup()
                        .addComponent(jLabel143)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jLabel130, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel131, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel128, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel129, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regularPanelLayout.createSequentialGroup()
                        .addComponent(regGrassPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        regularPanelLayout.setVerticalGroup(
            regularPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel130)
                    .addComponent(jLabel131)
                    .addComponent(jLabel128)
                    .addComponent(jLabel129)
                    .addComponent(jLabel143))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regGrassPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        regularPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Double Battle", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel132.setText("Minimum Level");

        jLabel133.setText("Maximum Level");

        jLabel134.setText("Species");

        jLabel135.setText("Form");

        jLabel144.setText("Chance");

        doubleGrassPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout doubleGrassPanelLayout = new javax.swing.GroupLayout(doubleGrassPanel);
        doubleGrassPanel.setLayout(doubleGrassPanelLayout);
        doubleGrassPanelLayout.setHorizontalGroup(
            doubleGrassPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        doubleGrassPanelLayout.setVerticalGroup(
            doubleGrassPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout regularPanel1Layout = new javax.swing.GroupLayout(regularPanel1);
        regularPanel1.setLayout(regularPanel1Layout);
        regularPanel1Layout.setHorizontalGroup(
            regularPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regularPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel144)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jLabel134, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel135, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel132, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel133, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regularPanel1Layout.createSequentialGroup()
                        .addComponent(doubleGrassPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        regularPanel1Layout.setVerticalGroup(
            regularPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel134)
                    .addComponent(jLabel135)
                    .addComponent(jLabel132)
                    .addComponent(jLabel133)
                    .addComponent(jLabel144))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doubleGrassPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        regularPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rare", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel136.setText("Minimum Level");

        jLabel137.setText("Maximum Level");

        jLabel138.setText("Species");

        jLabel139.setText("Form");

        jLabel145.setText("Chance");

        rareGrassPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout rareGrassPanelLayout = new javax.swing.GroupLayout(rareGrassPanel);
        rareGrassPanel.setLayout(rareGrassPanelLayout);
        rareGrassPanelLayout.setHorizontalGroup(
            rareGrassPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        rareGrassPanelLayout.setVerticalGroup(
            rareGrassPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout regularPanel2Layout = new javax.swing.GroupLayout(regularPanel2);
        regularPanel2.setLayout(regularPanel2Layout);
        regularPanel2Layout.setHorizontalGroup(
            regularPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regularPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel145)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jLabel138, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel139, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel136, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel137, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regularPanel2Layout.createSequentialGroup()
                        .addComponent(rareGrassPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        regularPanel2Layout.setVerticalGroup(
            regularPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel138)
                    .addComponent(jLabel139)
                    .addComponent(jLabel136)
                    .addComponent(jLabel137)
                    .addComponent(jLabel145))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rareGrassPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(regularPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regularPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(regularPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(regularPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(regularPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(regularPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Grass Encounters", jPanel1);

        regularPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Regular", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel153.setText("Minimum Level");

        jLabel154.setText("Maximum Level");

        jLabel155.setText("Species");

        jLabel156.setText("Form");

        jLabel157.setText("Chance");

        regSurfPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout regSurfPanelLayout = new javax.swing.GroupLayout(regSurfPanel);
        regSurfPanel.setLayout(regSurfPanelLayout);
        regSurfPanelLayout.setHorizontalGroup(
            regSurfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        regSurfPanelLayout.setVerticalGroup(
            regSurfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout regularPanel5Layout = new javax.swing.GroupLayout(regularPanel5);
        regularPanel5.setLayout(regularPanel5Layout);
        regularPanel5Layout.setHorizontalGroup(
            regularPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regularPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel157)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jLabel155, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel156, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel153, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel154, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regularPanel5Layout.createSequentialGroup()
                        .addComponent(regSurfPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        regularPanel5Layout.setVerticalGroup(
            regularPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel155)
                    .addComponent(jLabel156)
                    .addComponent(jLabel153)
                    .addComponent(jLabel154)
                    .addComponent(jLabel157))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regSurfPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        regularPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rare", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel158.setText("Minimum Level");

        jLabel159.setText("Maximum Level");

        jLabel160.setText("Species");

        jLabel161.setText("Form");

        jLabel162.setText("Chance");

        rareSurfPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout rareSurfPanelLayout = new javax.swing.GroupLayout(rareSurfPanel);
        rareSurfPanel.setLayout(rareSurfPanelLayout);
        rareSurfPanelLayout.setHorizontalGroup(
            rareSurfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        rareSurfPanelLayout.setVerticalGroup(
            rareSurfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout regularPanel6Layout = new javax.swing.GroupLayout(regularPanel6);
        regularPanel6.setLayout(regularPanel6Layout);
        regularPanel6Layout.setHorizontalGroup(
            regularPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regularPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel162)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jLabel160, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel161, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel158, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel159, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regularPanel6Layout.createSequentialGroup()
                        .addComponent(rareSurfPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        regularPanel6Layout.setVerticalGroup(
            regularPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel160)
                    .addComponent(jLabel161)
                    .addComponent(jLabel158)
                    .addComponent(jLabel159)
                    .addComponent(jLabel162))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rareSurfPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(regularPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regularPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 827, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(regularPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(regularPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Surf Encounters", jPanel2);

        regularPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Regular", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel163.setText("Minimum Level");

        jLabel164.setText("Maximum Level");

        jLabel165.setText("Species");

        jLabel166.setText("Form");

        jLabel167.setText("Chance");

        regFishPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout regFishPanelLayout = new javax.swing.GroupLayout(regFishPanel);
        regFishPanel.setLayout(regFishPanelLayout);
        regFishPanelLayout.setHorizontalGroup(
            regFishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        regFishPanelLayout.setVerticalGroup(
            regFishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout regularPanel7Layout = new javax.swing.GroupLayout(regularPanel7);
        regularPanel7.setLayout(regularPanel7Layout);
        regularPanel7Layout.setHorizontalGroup(
            regularPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regularPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel167)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jLabel165, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel166, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel163, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel164, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regularPanel7Layout.createSequentialGroup()
                        .addComponent(regFishPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        regularPanel7Layout.setVerticalGroup(
            regularPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel165)
                    .addComponent(jLabel166)
                    .addComponent(jLabel163)
                    .addComponent(jLabel164)
                    .addComponent(jLabel167))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regFishPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        regularPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rare", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel168.setText("Minimum Level");

        jLabel169.setText("Maximum Level");

        jLabel170.setText("Species");

        jLabel171.setText("Form");

        jLabel172.setText("Chance");

        rareFishPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout rareFishPanelLayout = new javax.swing.GroupLayout(rareFishPanel);
        rareFishPanel.setLayout(rareFishPanelLayout);
        rareFishPanelLayout.setHorizontalGroup(
            rareFishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        rareFishPanelLayout.setVerticalGroup(
            rareFishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout regularPanel8Layout = new javax.swing.GroupLayout(regularPanel8);
        regularPanel8.setLayout(regularPanel8Layout);
        regularPanel8Layout.setHorizontalGroup(
            regularPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regularPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel172)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jLabel170, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel171, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel168, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel169, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regularPanel8Layout.createSequentialGroup()
                        .addComponent(rareFishPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        regularPanel8Layout.setVerticalGroup(
            regularPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regularPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regularPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel170)
                    .addComponent(jLabel171)
                    .addComponent(jLabel168)
                    .addComponent(jLabel169)
                    .addComponent(jLabel172))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rareFishPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(regularPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regularPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 827, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(regularPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(regularPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Fishing Encounters", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel doubleGrassPanel;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel rareFishPanel;
    private javax.swing.JPanel rareGrassPanel;
    private javax.swing.JPanel rareSurfPanel;
    private javax.swing.JPanel regFishPanel;
    private javax.swing.JPanel regGrassPanel;
    private javax.swing.JPanel regSurfPanel;
    private javax.swing.JPanel regularPanel;
    private javax.swing.JPanel regularPanel1;
    private javax.swing.JPanel regularPanel2;
    private javax.swing.JPanel regularPanel5;
    private javax.swing.JPanel regularPanel6;
    private javax.swing.JPanel regularPanel7;
    private javax.swing.JPanel regularPanel8;
    // End of variables declaration//GEN-END:variables

}
