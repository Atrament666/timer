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

import javax.annotation.PostConstruct;
import javax.swing.JMenu;
import org.atrament.timer7.presets.actions.NewPresetAction;
import org.atrament.timer7.presets.actions.PresetMenuAction;
import org.atrament.timer7.presets.model.Preset;
import org.atrament.timer7.presets.model.PresetManager;
import org.atrament.timer7.mainwindow.ui.MainWindow;
import org.atrament.timer7.presets.model.Preset;
import org.atrament.timer7.presets.model.PresetManager;
import org.atrament.timer7.timerwidget.ui.TimerWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
@Component
@DependsOn("mainWindow")
public class PresetMenu extends JMenu {

    private PresetManager presetManager;
    private MainWindow mainWindow;
    private TimerWidget timerWidget;
    private NewPresetAction newPresetAction;
    private ApplicationContext ctx;

    public PresetMenu() {

    }

    @PostConstruct
    private void initComponent() {
        setText("Presets");
        add(newPresetAction);
        addSeparator();
        for (Preset preset : presetManager.getPresets()) {
            PresetMenuAction pa = new PresetMenuAction();
            pa.setPreset(preset);
            if (mainWindow.getMainTimerWidget() != null) {
                pa.setTimerWidget(mainWindow.getMainTimerWidget());
                System.out.println("Timer widget not null in PresetMenu");
            } else {
                System.out.println("Timer widget is null in PresetMenu");
            }
            //pa.setTimerWidget(mainWindow.getMainTimerWidget());
            add(pa);
        }
        
    }

    @Autowired
    public void setPresetManager(PresetManager presetManager) {
        this.presetManager = presetManager;
    }

    
    public void setTimerWidget(TimerWidget timerWidget) {
        this.timerWidget = timerWidget;
    }

    @Autowired
    public void setNewPresetAction(NewPresetAction newPresetAction) {
        this.newPresetAction = newPresetAction;
    }
    
    public void update() {
        removeAll();
        initComponent();
    }

    @Autowired
    public void setCtx(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Autowired
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    
    
    
    
    

    
}
