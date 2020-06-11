// This file is part of MicropolisJ.
// Copyright (C) 2013 Jason Long
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// MicropolisJ is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.gui;

import static micropolisj.gui.ColorParser.parseColor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import micropolisj.engine.Micropolis;
import micropolisj.engine.MicropolisMessage;
import micropolisj.engine.ZoneStatus;

public class NotificationPane extends JPanel {

    static final Dimension VIEWPORT_SIZE = new Dimension(160, 160);
    static final Color QUERY_COLOR = new Color(255, 165, 0);
    static final ResourceBundle GUI_STRINGS = MainWindow.guiStrings;
    static final ResourceBundle CITY_MESSAGES_STRINGS = ResourceBundle.getBundle("strings/CityMessages");
    static final ResourceBundle STATUS_STRINGS = ResourceBundle.getBundle("strings/StatusMessages");
    JLabel headerLabel;
    JViewport mapViewport;
    MicropolisDrawingArea mapView;
    JPanel mainPane;
    JComponent infoPane;

    public NotificationPane(Micropolis engine) {
        super(new BorderLayout());
        setVisible(false);

        headerLabel = new JLabel();
        headerLabel.setOpaque(true);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        add(headerLabel, BorderLayout.NORTH);

        JButton dismissBtn = new JButton(GUI_STRINGS.getString("notification.dismiss"));
        dismissBtn.addActionListener(evt -> onDismissClicked());
        add(dismissBtn, BorderLayout.SOUTH);

        mainPane = new JPanel(new BorderLayout());
        add(mainPane, BorderLayout.CENTER);

        JPanel viewportContainer = new JPanel(new BorderLayout());
        viewportContainer.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(8, 4, 8, 4),
                        BorderFactory.createLineBorder(Color.BLACK)
                ));
        mainPane.add(viewportContainer, BorderLayout.WEST);

        mapViewport = new JViewport();
        mapViewport.setPreferredSize(VIEWPORT_SIZE);
        mapViewport.setMaximumSize(VIEWPORT_SIZE);
        mapViewport.setMinimumSize(VIEWPORT_SIZE);
        viewportContainer.add(mapViewport, BorderLayout.CENTER);

        mapView = new MicropolisDrawingArea(engine);
        mapViewport.setView(mapView);
    }

    private void onDismissClicked() {
        setVisible(false);
    }

    void setPicture(Micropolis engine, int xpos, int ypos) {
        Dimension sz = VIEWPORT_SIZE;

        mapView.setEngine(engine);
        Rectangle r = mapView.getTileBounds(xpos, ypos);

        mapViewport.setViewPosition(new Point(
                r.x + r.width / 2 - sz.width / 2,
                r.y + r.height / 2 - sz.height / 2
        ));
    }

    public void showMessage(Micropolis engine, MicropolisMessage msg, int xpos, int ypos) {
        setPicture(engine, xpos, ypos);

        if (infoPane != null) {
            mainPane.remove(infoPane);
            infoPane = null;
        }

        headerLabel.setText(CITY_MESSAGES_STRINGS.getString(msg.name() + ".title"));
        headerLabel.setBackground(parseColor(CITY_MESSAGES_STRINGS.getString(msg.name() + ".color")));

        JLabel myLabel = new JLabel("<html><p>" + CITY_MESSAGES_STRINGS.getString(msg.name() + ".detail") + "</p></html>");
        myLabel.setPreferredSize(new Dimension(1, 1));

        infoPane = myLabel;
        mainPane.add(myLabel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void showZoneStatus(Micropolis engine, int xpos, int ypos, ZoneStatus zone) {
        headerLabel.setText(GUI_STRINGS.getString("notification.query_hdr"));
        headerLabel.setBackground(QUERY_COLOR);

        String buildingStr = zone.building != -1 ? STATUS_STRINGS.getString("zone." + zone.building) : "";
        String popDensityStr = STATUS_STRINGS.getString("status." + zone.popDensity);
        String landValueStr = STATUS_STRINGS.getString("status." + zone.landValue);
        String crimeLevelStr = STATUS_STRINGS.getString("status." + zone.crimeLevel);
        String pollutionStr = STATUS_STRINGS.getString("status." + zone.pollution);
        String growthRateStr = STATUS_STRINGS.getString("status." + zone.growthRate);

        setPicture(engine, xpos, ypos);

        if (infoPane != null) {
            mainPane.remove(infoPane);
            infoPane = null;
        }

        JPanel p = new JPanel(new GridBagLayout());
        mainPane.add(p, BorderLayout.CENTER);
        infoPane = p;

        GridBagConstraints c1 = new GridBagConstraints();
        GridBagConstraints c2 = new GridBagConstraints();

        c1.gridx = 0;
        c2.gridx = 1;
        c1.gridy = c2.gridy = 0;
        c1.anchor = GridBagConstraints.WEST;
        c2.anchor = GridBagConstraints.WEST;
        c1.insets = new Insets(0, 0, 0, 8);
        c2.weightx = 1.0;

        p.add(new JLabel(GUI_STRINGS.getString("notification.zone_lbl")), c1);
        p.add(new JLabel(buildingStr), c2);

        c1.gridy = ++c2.gridy;
        p.add(new JLabel(GUI_STRINGS.getString("notification.density_lbl")), c1);
        p.add(new JLabel(popDensityStr), c2);

        c1.gridy = ++c2.gridy;
        p.add(new JLabel(GUI_STRINGS.getString("notification.value_lbl")), c1);
        p.add(new JLabel(landValueStr), c2);

        c1.gridy = ++c2.gridy;
        p.add(new JLabel(GUI_STRINGS.getString("notification.crime_lbl")), c1);
        p.add(new JLabel(crimeLevelStr), c2);

        c1.gridy = ++c2.gridy;
        p.add(new JLabel(GUI_STRINGS.getString("notification.pollution_lbl")), c1);
        p.add(new JLabel(pollutionStr), c2);

        c1.gridy = ++c2.gridy;
        p.add(new JLabel(GUI_STRINGS.getString("notification.growth_lbl")), c1);
        p.add(new JLabel(growthRateStr), c2);

        c1.gridy++;
        c1.gridwidth = 2;
        c1.weighty = 1.0;
        p.add(new JLabel(), c1);

        setVisible(true);
    }
}
