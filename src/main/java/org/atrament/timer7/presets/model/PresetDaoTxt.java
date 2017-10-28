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

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
public class PresetDaoTxt implements PresetDao {

    private final String FILENAME;
    private final File file;

    public PresetDaoTxt() {
        this.FILENAME = System.getProperty("user.home") + File.separator + ".timer" + File.separator + "presets.txt";
        file = new File(FILENAME);

    }

    @Override
    public List<Preset> loadPresets() {
        List<Preset> result = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            if (file.exists() && file.canRead()) {
                while (sc.hasNext()) {
                    String line = sc.next();
                    result.add(Preset.parsePreset(line));
                }
            }

        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(PresetDaoTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void storePresets(List<Preset> presets) {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            catch (IOException ex) {
                Logger.getLogger(PresetDaoTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Preset preset: presets) {
                writer.append(preset.toString());
                writer.newLine();
            }
            
        }
        catch (IOException ex) {
            Logger.getLogger(PresetDaoTxt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
