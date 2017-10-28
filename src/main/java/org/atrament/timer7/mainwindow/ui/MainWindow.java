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
package org.atrament.timer7.mainwindow.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;
import org.atrament.timer7.presets.model.Preset;
import org.atrament.timer7.mainmenu.MainMenu;
import org.atrament.timer7.timerwidget.ui.TimerWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atrament <atrament666@gmail.com>
 */
@Component
public class MainWindow extends JFrame implements ActionListener{

    private final TimerWidget mainTimerWidget;
    private JButton startButton, cancelButton;

    private MainMenu mainMenu;

    public MainWindow(ApplicationContext ctx) {
        mainTimerWidget = new TimerWidget();
    }

    @PostConstruct
    private void initComponent() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Unable to set LookAndFeel...");
            System.out.println(e.toString());
        }
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/icon-16.png")));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                setExtendedState(JFrame.ICONIFIED);
            }

        });
        setTitle("Timer");
        setResizable(false);
        setLayout(new MigLayout());

        mainTimerWidget.addActionListener(this);
        add(mainTimerWidget, "wrap");
        setJMenuBar(mainMenu);
        startButton = new JButton("Start");
        startButton.addActionListener((e) -> {
            mainTimerWidget.startTimer();
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((e) -> {
            mainTimerWidget.stopTimer();
            mainTimerWidget.reset();
        });
        add(startButton, "split 2, growx");
        add(cancelButton, "growx");
        setStoppedState();
        pack();
        setLocationRelativeTo(null);
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });

    }

    public void setRunningState() {
        startButton.setEnabled(false);
        cancelButton.setEnabled(true);
    }

    public void setStoppedState() {
        startButton.setEnabled(true);
        cancelButton.setEnabled(false);
    }

    public void setPreset(Preset preset) {
        mainTimerWidget.setPreset(preset);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("stop")) {
            setStoppedState();
        }
        if (e.getActionCommand().equals("run")) {
            setRunningState();
        }
    }

    @Autowired
    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public TimerWidget getMainTimerWidget() {
        return mainTimerWidget;
    }

}
