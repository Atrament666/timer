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
import javax.swing.AbstractAction;
import org.apache.log4j.Logger;
import org.atrament.timer7.presets.model.Preset;
import org.atrament.timer7.timerwidget.ui.TimerWidget;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
public class PresetMenuAction extends AbstractAction {

    private TimerWidget timerWidget;
    private Preset preset;

    private final Logger log = Logger.getLogger(PresetMenuAction.class);

    public PresetMenuAction() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        log.info("Setting the preset " + preset);
        timerWidget.setPreset(preset);
    }

    @Override
    public String toString() {
        return preset.toString();
    }

    public void setTimerWidget(TimerWidget timerWidget) {
        this.timerWidget = timerWidget;
    }

    public void setPreset(Preset preset) {
        this.preset = preset;
        putValue(NAME, preset.toString());
    }

}
