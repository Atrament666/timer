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
package org.atrament.timer7.presets.model;

import org.atrament.timer7.presets.ui.PresetMenu;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
@Component
public class PresetManager {
    
    
    private List<Preset> presets;
    private PresetDao presetDao;
    private PresetMenu presetMenu;

    public PresetManager() {
        //this.presetDao = new PresetDaoMock();
        
        
    }
    
    public void addPreset(Preset preset) {
        presets.add(preset);
        presetDao.storePresets(presets);
        presetMenu.update();
    }
    
    public void removePreset(Preset preset) {
        presets.remove(preset);
        presetDao.storePresets(presets);
        presetMenu.update();
    }

    public List<Preset> getPresets() {
        return presets;
    }

    @Autowired
    public void setPresetMenu(PresetMenu presetMenu) {
        this.presetMenu = presetMenu;
    }

    @Autowired
    public void setPresetDao(PresetDao presetDao) {
        this.presetDao = presetDao;
        presets = presetDao.loadPresets();
    }
    
    
    
    
    
    
    
    
    
   
    
    
    
}
