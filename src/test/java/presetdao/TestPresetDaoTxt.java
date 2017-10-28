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
package presetdao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.atrament.timer7.presets.model.Preset;
import org.atrament.timer7.presets.model.PresetDaoTxt;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
public class TestPresetDaoTxt {
    
    private static File f;

    public TestPresetDaoTxt() {
        
    }

    @BeforeClass
    public static void setUpClass() {
        f = new File(TestPresetDaoTxt.class.getClassLoader().getResource("test.txt").getFile());
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testFileExists() {
        //File f = new File(getClass().getClassLoader().getResource("test.txt").getFile());
        Assert.assertTrue(f.exists());
    }
    
    @Test
    public void testReadingFromTestFile() {
        List<Preset> presets = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.next();
                presets.add(Preset.parsePreset(line));
            }
            Assert.assertTrue(presets.get(0).getName().equals("Těstoviny"));
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(TestPresetDaoTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testParsingOfPreset() {
        Preset testPreset = new Preset();
        testPreset.setName("Těstoviny");
        testPreset.setHours(0);
        testPreset.setMinutes(11);
        testPreset.setSeconds(0);
        
        String testString = "Těstoviny-00:11:00";
        Preset parsedPreset = Preset.parsePreset(testString);
        Assert.assertEquals(testPreset, parsedPreset);
    
}
}
