package org.example.UI.Components;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class PlayerFormDialog extends JDialog {
    private final JTextField txtNombre  = new JTextField();
    private final JTextField txtPais    = new JTextField();
    private final JTextField txtEdad    = new JTextField();
    private final JTextField txtPuntaje = new JTextField();
    private boolean accepted = false;

    public PlayerFormDialog(Window owner) {
        super(owner, "Ingresar tenista", ModalityType.APPLICATION_MODAL);
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(4,2,8,8));
        form.add(new JLabel("Nombre:"));  form.add(txtNombre);
        form.add(new JLabel("País:"));    form.add(txtPais);
        form.add(new JLabel("Edad:"));    form.add(txtEdad);
        form.add(new JLabel("Puntaje:")); form.add(txtPuntaje);
        add(form, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancel = new JButton("Cancelar");
        JButton ok     = new JButton("Guardar");
        actions.add(cancel); actions.add(ok);
        add(actions, BorderLayout.SOUTH);

        cancel.addActionListener(e -> { accepted = false; dispose(); });
        ok.addActionListener(e -> {
            if (validateFields()) { accepted = true; dispose(); }
        });

        setSize(360, 220);
        setLocationRelativeTo(owner);
    }

    private boolean validateFields() {
        if (txtNombre.getText().trim().isEmpty()) { msg("Nombre requerido"); return false; }
        if (txtPais.getText().trim().isEmpty())   { msg("País requerido"); return false; }
        try {
            int edad = Integer.parseInt(txtEdad.getText().trim());
            if (edad <= 0) { msg("Edad debe ser > 0"); return false; }
            int puntaje = Integer.parseInt(txtPuntaje.getText().trim());
            if (puntaje < 0) { msg("Puntaje no puede ser negativo"); return false; }
        } catch (NumberFormatException ex) {
            msg("Edad/Puntaje deben ser números enteros");
            return false;
        }
        return true;
    }

    private void msg(String m) { JOptionPane.showMessageDialog(this, m); }

    public boolean isAccepted() { return accepted; }


    public Optional<String[]> getValues() {
        if (!accepted) return Optional.empty();
        return Optional.of(new String[]{
                txtNombre.getText().trim(),
                txtPais.getText().trim(),
                txtEdad.getText().trim(),
                txtPuntaje.getText().trim()
        });
    }


    public void setInitial(String nombre, String pais, String edad, String puntaje) {
        txtNombre.setText(nombre);
        txtPais.setText(pais);
        txtEdad.setText(edad);
        txtPuntaje.setText(puntaje);
    }
}
