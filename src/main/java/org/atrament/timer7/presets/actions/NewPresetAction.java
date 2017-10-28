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
package org.atrament.timer7.presets.actions;

import java.awt.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import org.atrament.timer7.presets.model.PresetManager;
import org.atrament.timer7.presets.ui.PresetDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
@Component
public class NewPresetAction extends AbstractAction {
    
    private PresetDialog presetDialog;
    private PresetManager presetManager;

    public NewPresetAction() {
       
    }
    
    @PostConstruct
    private void initComponent() {
        putValue(NAME, "Add a preset");    
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            presetDialog.setVisible(true);
            if (presetDialog.isPresetReady()) {
                presetManager.addPreset(presetDialog.getPreset());
            }
        });
    }

    @Autowired
    public void setPresetDialog(PresetDialog presetDialog) {
        this.presetDialog = presetDialog;
    }

    @Autowired
    public void setPresetManager(PresetManager presetManager) {
        this.presetManager = presetManager;
    }
    
    
    
    

}
