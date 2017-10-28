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
package org.atrament.timer7.timerspinner.model;

import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Atrament
 */
public class TimerSpinnerModel extends SpinnerNumberModel {

    private TimerSpinnerModel linkedModel;

    public TimerSpinnerModel(int maximum) {
        super(0, 0, maximum, 1);
        this.linkedModel = null;

    }

    public void setLinkedModel(TimerSpinnerModel linkedModel) {
        this.linkedModel = linkedModel;
    }

    @Override
    public Object getPreviousValue() {
        Object value = super.getPreviousValue();
        if (value == null) {

            value = (Integer) getMaximum();
            if (linkedModel != null) {
                linkedModel.setValue((Integer) linkedModel.getPreviousValue());
            }
        }
        return value;
    }

    @Override
    public Object getNextValue() {
        Object value = super.getNextValue();
        if (value == null) {
            value = (Integer) getMinimum();
            if (linkedModel != null) {
                linkedModel.setValue((Integer) linkedModel.getNextValue());
            }
        }
        return value;
    }

}
