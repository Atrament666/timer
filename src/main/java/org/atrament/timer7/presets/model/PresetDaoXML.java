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

import org.atrament.timer7.presets.model.Preset;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.atrament.timer7.presets.model.PresetDao;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
@XmlRootElement
public class PresetDaoXML implements PresetDao {
    
    private static final String FILENAME = System.getProperty("user.home") + File.separator + ".timer" + File.separator + "presets.xml";
    private final File file;
    
    @XmlElement(name="preset")
    private List<Preset> presets;

    public PresetDaoXML() {
        file = new File(FILENAME);
        presets = new ArrayList<>();
    }
    
    

    @Override
    public List<Preset> loadPresets() {
        
        Preset p = new Preset();
        p.setName("Rýže");
        p.setMinutes(11);
        presets.add(p);
        return presets;
    }

    @Override
    public void storePresets(List<Preset> presets) {
        try {
            this.presets = presets;
            JAXBContext context = JAXBContext.newInstance(PresetDaoXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(this.presets, System.out);
        }
        catch (JAXBException ex) {
            Logger.getLogger(PresetDaoXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Preset> getPresets() {
        return presets;
    }
    
    
    
}
