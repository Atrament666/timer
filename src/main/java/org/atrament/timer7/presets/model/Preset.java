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

import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
@XmlRootElement
public class Preset {

    private String name;
    private int hours = 0, minutes = 0, seconds = 0;

    public Preset(String name) {
        this.name = name;
    }

    public Preset() {
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    @XmlElement
    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    @XmlElement
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    @XmlElement
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return name + "-" + hours + ":" + minutes + ":" + seconds;
    }
    
    public static Preset parsePreset(String string) {
        Preset result = new Preset();
        String[] nameTime = string.split("-");
        if (nameTime.length < 2) {
            System.out.println("Error parsing preset");
            return null;
        }
        if (nameTime[0].equals("")) {
            System.out.println("Missing name of the preset");
            return null;
        }
        if (nameTime[1].equals("")) {
            System.out.println("Missing time part of the preset");
            return null;
        }
        String[] time = nameTime[1].split(":");
        if (time.length < 3) {
            System.out.println("Error parsing time part of the preset");
            return null;
        }
        result.setName(nameTime[0]);
        result.setHours(Integer.parseInt(time[0]));
        result.setMinutes(Integer.parseInt(time[1]));
        result.setSeconds(Integer.parseInt(time[2]));
        return result;
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + this.hours;
        hash = 59 * hash + this.minutes;
        hash = 59 * hash + this.seconds;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Preset other = (Preset) obj;
        if (this.hours != other.hours) {
            return false;
        }
        if (this.minutes != other.minutes) {
            return false;
        }
        if (this.seconds != other.seconds) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    
    

}
