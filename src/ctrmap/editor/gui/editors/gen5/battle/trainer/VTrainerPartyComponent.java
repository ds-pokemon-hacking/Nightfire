/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ctrmap.editor.gui.editors.gen5.battle.trainer;

import ctrmap.editor.CTRMap;
import ctrmap.formats.pokemon.gen5.battle.trainer.WBTrainerPoke;
import ctrmap.formats.pokemon.text.TextFile;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author platinum
 */
public class VTrainerPartyComponent extends javax.swing.JPanel {

    /**
     * Creates new form VTrainerEditorPartyComponent
     */
    public VTrainerPartyComponent(CTRMap Instance, WBTrainerPoke Pkmn, TextFile PkmnNames, TextFile ItemNames, TextFile MoveNames) {
        initComponents();        

        // Set species selection.
        speciesComboBox.setModel(new DefaultComboBoxModel(PkmnNames.getFriendlyLinesArray()));
        
        // Set held item selection.
        heldItemComboBox.setModel(new DefaultComboBoxModel(ItemNames.getFriendlyLinesArray()));
        
        // Set move selection.
        String[] MoveNamesFriendly = MoveNames.getFriendlyLinesArray();
        Move1ComboBox.setModel(new DefaultComboBoxModel(MoveNamesFriendly));
        Move2ComboBox.setModel(new DefaultComboBoxModel(MoveNamesFriendly));
        Move3ComboBox.setModel(new DefaultComboBoxModel(MoveNamesFriendly));
        Move4ComboBox.setModel(new DefaultComboBoxModel(MoveNamesFriendly));

        // Initialize all component values.
        // Want it in a separate method for cleanliness.
        // This must come after the model sets!
        UI_Init(Instance, Pkmn);
        
        // Add handlers to track value changes.
        speciesComboBox.addActionListener((ActionEvent ae) -> {
            if (Instance != null) {
                int SelIndex = speciesComboBox.getSelectedIndex();
                if (Pkmn != null && SelIndex >= 0) {
                    Pkmn.SetSpecies(SelIndex);
                }      
            }
        });
        levelSpinner.addChangeListener((ChangeEvent ce) -> {
            if (Instance != null) {
                int SelVal = (int) levelSpinner.getValue();
                if (Pkmn != null && SelVal >= 0) {
                    Pkmn.SetLevel(SelVal);
                }
            }   
        });
        formSpinner.addChangeListener((ChangeEvent ce) -> {
            if (Instance != null) {
                int SelVal = (int) formSpinner.getValue();
                if (Pkmn != null && SelVal >= 0) {
                    Pkmn.SetForm(SelVal);
                }
            }   
        });
        abilityComboBox.addActionListener((ActionEvent ae) -> {
            if (Instance != null) {
                int SelIndex = abilityComboBox.getSelectedIndex();
                if (Pkmn != null && SelIndex >= 0) {
                    Pkmn.SetAbility(SelIndex);
                }      
            }
        });
        heldItemComboBox.addActionListener((ActionEvent ae) -> {
            if (Instance != null) {
                int SelIndex = heldItemComboBox.getSelectedIndex();
                if (Pkmn != null && SelIndex >= 0) {
                    Pkmn.SetHeldItem(SelIndex);
                }      
            }
        });
        genderComboBox.addActionListener((ActionEvent ae) -> {
            if (Instance != null) {
                int SelIndex = genderComboBox.getSelectedIndex();
                if (Pkmn != null && SelIndex >= 0) {
                    Pkmn.SetGender(SelIndex);
                }      
            }
        });
        ivSpinner.addChangeListener((ChangeEvent ce) -> {
            if (Instance != null) {
                int SelVal = (int) ivSpinner.getValue();
                if (Pkmn != null && SelVal >= 0) {
                    Pkmn.SetIV(SelVal);
                }
            }   
        });
        Move1ComboBox.addActionListener((ActionEvent ae) -> {
            if (Instance != null) {
                int SelIndex = Move1ComboBox.getSelectedIndex();
                if (Pkmn != null && SelIndex >= 0) {
                    Pkmn.SetMove(0, SelIndex);
                }      
            }
        });
        Move2ComboBox.addActionListener((ActionEvent ae) -> {
            if (Instance != null) {
                int SelIndex = Move2ComboBox.getSelectedIndex();
                if (Pkmn != null && SelIndex >= 0) {
                    Pkmn.SetMove(0, SelIndex);
                }      
            }
        });
        Move3ComboBox.addActionListener((ActionEvent ae) -> {
            if (Instance != null) {
                int SelIndex = Move3ComboBox.getSelectedIndex();
                if (Pkmn != null && SelIndex >= 0) {
                    Pkmn.SetMove(0, SelIndex);
                }      
            }
        });
        Move4ComboBox.addActionListener((ActionEvent ae) -> {
            if (Instance != null) {
                int SelIndex = Move4ComboBox.getSelectedIndex();
                if (Pkmn != null && SelIndex >= 0) {
                    Pkmn.SetMove(0, SelIndex);
                }      
            }
        });
    }
    
    private void UI_Init(CTRMap Instance, WBTrainerPoke Pkmn) {
        // Initialize the UI with initial Pokemon values.
        if (Instance != null && Pkmn != null) {
            speciesComboBox.setSelectedIndex(Pkmn.GetSpecies());
            levelSpinner.setValue(Pkmn.GetLevel());
            formSpinner.setValue(Pkmn.GetForm());
            abilityComboBox.setSelectedIndex(Pkmn.GetAbility());
            heldItemComboBox.setSelectedIndex(Pkmn.GetHeldItem());
            genderComboBox.setSelectedIndex(Pkmn.GetGender());
            ivSpinner.setValue(Pkmn.GetIV());
            Move1ComboBox.setSelectedIndex(Pkmn.GetMove(0));
            Move2ComboBox.setSelectedIndex(Pkmn.GetMove(1));
            Move3ComboBox.setSelectedIndex(Pkmn.GetMove(2));
            Move4ComboBox.setSelectedIndex(Pkmn.GetMove(3));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        previewLabel = new javax.swing.JLabel();
        speciesLabel = new javax.swing.JLabel();
        speciesComboBox = new javax.swing.JComboBox<>();
        levelLabel = new javax.swing.JLabel();
        levelSpinner = new javax.swing.JSpinner();
        formLabel = new javax.swing.JLabel();
        formSpinner = new javax.swing.JSpinner();
        abilityLabel = new javax.swing.JLabel();
        abilityComboBox = new javax.swing.JComboBox<>();
        genderLabel = new javax.swing.JLabel();
        genderComboBox = new javax.swing.JComboBox<>();
        ivLabel = new javax.swing.JLabel();
        ivSpinner = new javax.swing.JSpinner();
        heldItemLabel = new javax.swing.JLabel();
        heldItemComboBox = new javax.swing.JComboBox<>();
        movesPanel = new javax.swing.JPanel();
        Move1ComboBox = new javax.swing.JComboBox<>();
        Move2ComboBox = new javax.swing.JComboBox<>();
        Move3ComboBox = new javax.swing.JComboBox<>();
        Move4ComboBox = new javax.swing.JComboBox<>();

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        previewLabel.setBackground(new java.awt.Color(102, 102, 102));
        previewLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        previewLabel.setText("Preview Here");
        previewLabel.setOpaque(true);

        speciesLabel.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        speciesLabel.setText("Species");

        speciesComboBox.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        speciesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disabled", "Enabled", "Used" }));

        levelLabel.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        levelLabel.setText("Level");

        levelSpinner.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N

        formLabel.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        formLabel.setText("Form");

        formSpinner.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N

        abilityLabel.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        abilityLabel.setText("Ability");

        abilityComboBox.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        abilityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Random", "Primary", "Secondary", "Hidden" }));

        genderLabel.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        genderLabel.setText("Gender");

        genderComboBox.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Random", "Male", "Female" }));

        ivLabel.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        ivLabel.setText("IVs");

        ivSpinner.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N

        heldItemLabel.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        heldItemLabel.setText("Held Item");

        heldItemComboBox.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        heldItemComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disabled", "Enabled", "Used" }));

        movesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Moves"));

        Move1ComboBox.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        Move1ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disabled", "Enabled", "Used" }));

        Move2ComboBox.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        Move2ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disabled", "Enabled", "Used" }));

        Move3ComboBox.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        Move3ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disabled", "Enabled", "Used" }));

        Move4ComboBox.setFont(new java.awt.Font("Droid Sans", 0, 12)); // NOI18N
        Move4ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disabled", "Enabled", "Used" }));

        javax.swing.GroupLayout movesPanelLayout = new javax.swing.GroupLayout(movesPanel);
        movesPanel.setLayout(movesPanelLayout);
        movesPanelLayout.setHorizontalGroup(
            movesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(movesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Move3ComboBox, 0, 220, Short.MAX_VALUE)
                    .addComponent(Move1ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(movesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Move2ComboBox, 0, 219, Short.MAX_VALUE)
                    .addComponent(Move4ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        movesPanelLayout.setVerticalGroup(
            movesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movesPanelLayout.createSequentialGroup()
                .addGroup(movesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(movesPanelLayout.createSequentialGroup()
                        .addComponent(Move2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(Move4ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(movesPanelLayout.createSequentialGroup()
                        .addComponent(Move1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(Move3ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(genderLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(levelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(abilityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                        .addComponent(heldItemLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(speciesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(heldItemComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(speciesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abilityComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(levelSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(formLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(movesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(ivLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ivSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(449, 449, 449))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(movesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(speciesLabel)
                                    .addComponent(speciesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(levelLabel)
                                    .addComponent(formLabel)
                                    .addComponent(formSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(levelSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(abilityLabel)
                                    .addComponent(abilityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(heldItemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(heldItemLabel))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ivLabel)
                                .addComponent(ivSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(genderLabel)))
                        .addGap(4, 4, 4))
                    .addComponent(previewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {genderComboBox, ivSpinner});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Move1ComboBox;
    private javax.swing.JComboBox<String> Move2ComboBox;
    private javax.swing.JComboBox<String> Move3ComboBox;
    private javax.swing.JComboBox<String> Move4ComboBox;
    private javax.swing.JComboBox<String> abilityComboBox;
    private javax.swing.JLabel abilityLabel;
    private javax.swing.JLabel formLabel;
    private javax.swing.JSpinner formSpinner;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JComboBox<String> heldItemComboBox;
    private javax.swing.JLabel heldItemLabel;
    private javax.swing.JLabel ivLabel;
    private javax.swing.JSpinner ivSpinner;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JSpinner levelSpinner;
    private javax.swing.JPanel movesPanel;
    private javax.swing.JLabel previewLabel;
    private javax.swing.JComboBox<String> speciesComboBox;
    private javax.swing.JLabel speciesLabel;
    // End of variables declaration//GEN-END:variables
}
