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
package org.atrament.timer7.mainmenu;

import javax.annotation.PostConstruct;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import org.atrament.timer7.mainwindow.actions.ExitAction;
import org.atrament.timer7.preferences.actions.PreferencesAction;
import org.atrament.timer7.presets.ui.PresetMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
@Component
@DependsOn("mainWindow")
public class MainMenu extends JMenuBar {
    
    private PresetMenu presetMenu;
    private PreferencesAction optionsAction;
    private ExitAction exitAction;
    
    public MainMenu() {
        super();
    }

    @PostConstruct
    private void initComponent() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(optionsAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);

        add(fileMenu);

        add(presetMenu);
        
    }

    @Autowired
    public void setExitAction(ExitAction exitAction) {
        this.exitAction = exitAction;
    }

    @Autowired
    public void setPresetMenu(PresetMenu presetMenu) {
        this.presetMenu = presetMenu;
    }

    @Autowired
    public void setOptionsAction(PreferencesAction optionsAction) {
        this.optionsAction = optionsAction;
    }
    
    
    
    

}
