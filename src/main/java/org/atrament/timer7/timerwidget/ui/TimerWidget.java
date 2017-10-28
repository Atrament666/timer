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
package org.atrament.timer7.timerwidget.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;
import org.atrament.timer7.mainwindow.Alarmer;
import org.atrament.timer7.preferences.PreferencesManager;
import org.atrament.timer7.presets.model.Preset;
import org.atrament.timer7.timerspinner.ui.TimerSpinner;
import org.atrament.timer7.timerwidget.Direction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
public class TimerWidget extends JPanel {

    private PreferencesManager preferencesManager;
    private Alarmer alarmer;

    private final List<ActionListener> listeners;
    private Timer timer;
    private TimerSpinner hours, minutes, seconds;
    private Direction direction;

    public TimerWidget() {
        listeners = new ArrayList<>();
        direction = Direction.DOWN;
        initComponent();
    }

    //@PostConstruct
    private void initComponent() {
        setLayout(new MigLayout());
        hours = new TimerSpinner(24);
        minutes = new TimerSpinner(59);
        seconds = new TimerSpinner(59);

        seconds.setLinkedSpinner(minutes);
        minutes.setLinkedSpinner(hours);

        add(new JLabel("Hours:"));
        add(new JLabel("Minutes:"));
        add(new JLabel("Seconds:"), "wrap");
        add(hours);
        add(minutes);
        add(seconds);

    }

    public void tick() {
        if (direction == Direction.DOWN) {
            if (isZero()) {
                if (!preferencesManager.getPrefs().getBoolean("timechange", false)) {
                    stopTimer();
                    //mainWindow.setStopped();
                    alarmer.beep();
                    direction = Direction.DOWN;
                } else {
                    alarmer.beep();
                    direction = Direction.UP;
                }

            } else {
                seconds.getModel().setValue(seconds.getModel().getPreviousValue());
            }

        }
        if (direction == Direction.UP) {
            seconds.getModel().setValue(seconds.getModel().getNextValue());
        }

    }

    public Boolean isZero() {
        return (((int) hours.getValue() == 0) && (int) minutes.getValue() == 0 && (int) seconds.getValue() == 0);
    }

    public void startTimer() {
        timer = new Timer(1000, (e) -> {
            tick();
        });
        timer.start();
        fireRunEvent();

    }

    public void stopTimer() {
        timer.stop();
        fireStopEvent();
    }

    public void reset() {
        direction = Direction.DOWN;
        hours.setValue(0);
        minutes.setValue(0);
        seconds.setValue(0);
    }

    public void setPreset(Preset preset) {
        hours.setValue(preset.getHours());
        minutes.setValue(preset.getMinutes());
        seconds.setValue(preset.getSeconds());
    }

    public void addActionListener(ActionListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void fireStopEvent() {
        listeners.forEach((listener) -> {
            listener.actionPerformed(new ActionEvent(this, 0, "stop"));
        });
    }

    public void fireRunEvent() {
        listeners.forEach((listener) -> {
            listener.actionPerformed(new ActionEvent(this, 0, "run"));
        });
    }

    @Autowired
    public void setPreferencesManager(PreferencesManager preferencesManager) {
        this.preferencesManager = preferencesManager;
    }

    @Autowired
    public void setAlarmer(Alarmer alarmer) {
        this.alarmer = alarmer;
    }

    public TimerSpinner getHours() {
        return hours;
    }

    public TimerSpinner getMinutes() {
        return minutes;
    }

    public TimerSpinner getSeconds() {
        return seconds;
    }

}
