/*
 * Timer
 * Copyright (C) 2014 Atrament
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.atrament.timer7.preferences.ui;

import org.atrament.timer7.mainwindow.ui.MainWindow;
import java.awt.event.WindowEvent;
import javax.annotation.PostConstruct;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.atrament.timer7.preferences.PreferencesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atrament
 */
@Component
public class PreferencesDialog extends JDialog {
    
    
    private PreferencesManager preferencesManager;
    private MainWindow mainWindow;

    private JCheckBox minimizeCheckBox;
    private JCheckBox muteCheckBox;
    private JCheckBox timeCheckBox;

    public PreferencesDialog() {
    }

    @PostConstruct
    private void initComponents() {
        setResizable(false);
        setLayout(new MigLayout("", "[grow]push[]", "[grow]push[]"));
        setModalityType(ModalityType.APPLICATION_MODAL);
        JPanel panel = new JPanel(new MigLayout("", "[][][][]", "[][][]"));
        panel.setBorder(BorderFactory.createTitledBorder("Options:"));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        muteCheckBox = new JCheckBox("Mute sound");
        muteCheckBox.setSelected(preferencesManager.getPrefs().getBoolean("mute", false));
        panel.add(muteCheckBox, "wrap");

        minimizeCheckBox = new JCheckBox("Minimize on start");
        minimizeCheckBox.setSelected(preferencesManager.getPrefs().getBoolean("minimize", true));
        panel.add(minimizeCheckBox, "wrap");

        timeCheckBox = new JCheckBox("Continue counting up after the timer has finished");
        timeCheckBox.setSelected(preferencesManager.getPrefs().getBoolean("timechange", false));
        panel.add(timeCheckBox, "wrap");

        JButton okButton = new JButton("Ok");
        okButton.addActionListener((e) -> {
            saveConfig();
            close();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((e) -> {
            close();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(panel, "wrap, grow");
        add(buttonPanel, " align right");
        
        pack();
        setLocationRelativeTo(mainWindow);

    }

    private void saveConfig() {
        preferencesManager.getPrefs().putBoolean("minimize", minimizeCheckBox.isSelected());
        preferencesManager.getPrefs().putBoolean("mute", muteCheckBox.isSelected());
        preferencesManager.getPrefs().putBoolean("timechange", timeCheckBox.isSelected());

    }

    private void close() {
        setVisible(false);
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    @Autowired
    public void setPreferencesManager(PreferencesManager preferencesManager) {
        this.preferencesManager = preferencesManager;
    }

    @Autowired
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    

}
