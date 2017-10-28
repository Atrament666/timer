/*
 * Copyright (C) 2015 Atrament
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
package org.atrament.timer7.timerspinner.ui;

import java.awt.Font;
import javax.swing.JSpinner;
import org.atrament.timer7.timerspinner.model.TimerSpinnerModel;

/**
 *
 * @author Atrament
 */
public class TimerSpinner extends JSpinner {

    public TimerSpinner(int maximum) {
        initComponent(maximum);

    }

    private void initComponent(int maximum) {
        setEditor(new JSpinner.NumberEditor(this, "00"));
        setFont(new Font("Tahoma", Font.BOLD, 32));
        setModel(new TimerSpinnerModel(maximum));
    }

    public void setLinkedSpinner(TimerSpinner linkedSpinner) {
        TimerSpinnerModel model = (TimerSpinnerModel) getModel();
        model.setLinkedModel((TimerSpinnerModel) linkedSpinner.getModel());

    }
    
    
}
