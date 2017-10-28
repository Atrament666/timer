/*
 * Copyright (C) 2017 Atrament <atrament666@gmail.com>
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
package org.atrament.timer7.presets.ui;

import org.atrament.timer7.mainwindow.ui.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import org.atrament.timer7.presets.model.Preset;
import org.atrament.timer7.timerwidget.ui.TimerWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
@Component
public class PresetDialog extends JDialog implements ActionListener {

    private TimerWidget timerWidget;
    private MainWindow mainWindow;

    private Preset preset;

    private JTextField nameInput;
    private JButton saveButton, cancelButton;
    private Boolean isPresetReady;

    public PresetDialog() {
        this.isPresetReady = false;

    }

    @PostConstruct
    private void initComponent() {
        setTitle("New Preset...");
        setLayout(new MigLayout("", "", ""));
        setModalityType(ModalityType.APPLICATION_MODAL);

        add(new JLabel("Name: "));
        nameInput = new JTextField();
        add(nameInput, "wrap, growx");
        timerWidget = new TimerWidget();
        add(timerWidget, "wrap, span 2");
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        add(saveButton, " growx");
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        add(cancelButton, "growx");
        pack();
        setLocationRelativeTo(mainWindow);

    }

    public Preset getPreset() {
        return preset;
    }

    public void setPreset(Preset preset) {
        this.preset = preset;
    }

    @Autowired
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setTimerWidget(TimerWidget timerWidget) {
        this.timerWidget = timerWidget;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            if (nameInput.getText().equals("")) {
                System.out.println("You need to enter a name");
            } else {
                preset = new Preset(nameInput.getText());
                preset.setHours((int) timerWidget.getHours().getValue());
                preset.setMinutes((int) timerWidget.getMinutes().getValue());
                preset.setSeconds((int) timerWidget.getSeconds().getValue());
                isPresetReady = true;
                nameInput.setText("");
                timerWidget.reset();
                dispose();
            }
        }
        if (e.getSource() == cancelButton) {
            SwingUtilities.invokeLater(() -> {
                nameInput.setText("");
                timerWidget.reset();
                isPresetReady = false;
                dispose();
            });
        }
    }

    public Boolean isPresetReady() {
        return isPresetReady;
    }
    
    

}
