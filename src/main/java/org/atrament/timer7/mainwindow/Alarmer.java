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
package org.atrament.timer7.mainwindow;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
@Component
public class Alarmer {

    public Alarmer() {
    
    
    }
    
    public void beep() {
        SwingUtilities.invokeLater(() -> {
                try {
                    InputStream in = getClass().getClassLoader().getResourceAsStream("sound/beep.wav");
                    //the input stream needs to be decorated with BufferedInputStream in order to prevent java.io.IOException: mark/reset not supported
                    InputStream bin = new BufferedInputStream(in);
                    AudioInputStream ais = AudioSystem.getAudioInputStream(bin);
                    AudioFormat format = ais.getFormat();
                    DataLine.Info info = new DataLine.Info(Clip.class, format);
                    Clip clip = (Clip) AudioSystem.getLine(info);
                    clip.open(ais);
                    clip.start();
                    
                    
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(Alarmer.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
    }
    
    
    
}
