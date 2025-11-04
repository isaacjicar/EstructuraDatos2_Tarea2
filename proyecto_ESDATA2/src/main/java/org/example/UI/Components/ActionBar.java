package org.example.UI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionBar extends JPanel {
    private final JButton btnAdd     = new JButton("1) Ingresar tenista");
    private final JButton btnShow    = new JButton("2) Mostrar ingresados");
    private final JButton btnScore   = new JButton("3) Ordenar Puntaje ↓");
    private final JButton btnAge     = new JButton("4) Ordenar Edad ↑");
    private final JButton btnCountry = new JButton("5) Ordenar País+Nombre ↑");

    public ActionBar() {
        super(new GridLayout(1, 5, 8, 8));
        add(btnAdd); add(btnShow); add(btnScore); add(btnAge); add(btnCountry);
    }

    public void onAdd(ActionListener l)     { btnAdd.addActionListener(l); }
    public void onShow(ActionListener l)    { btnShow.addActionListener(l); }
    public void onScore(ActionListener l)   { btnScore.addActionListener(l); }
    public void onAge(ActionListener l)     { btnAge.addActionListener(l); }
    public void onCountry(ActionListener l) { btnCountry.addActionListener(l); }
}
