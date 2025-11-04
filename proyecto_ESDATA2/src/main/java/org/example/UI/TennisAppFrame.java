package org.example.UI;

import org.example.UI.Components.ActionBar;
import org.example.UI.Components.PlayerFormDialog;
import org.example.UI.Components.PlayersTable;
import org.example.model.TennisPlayer;
import org.example.repository.TennisRepo;

import javax.swing.*;
import java.awt.*;

public class TennisAppFrame extends JFrame {
    private final TennisRepo repo = new TennisRepo();
    private final PlayersTable playersTable = new PlayersTable();

    public TennisAppFrame() {
        super("Ranking ATP - Proyecto (Componentizado)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);


        ActionBar actionBar = new ActionBar();
        add(actionBar, BorderLayout.NORTH);


        add(playersTable, BorderLayout.CENTER);


        actionBar.onAdd(e -> onAdd());
        actionBar.onShow(e -> showOriginal());
        actionBar.onScore(e -> showByPuntaje());
        actionBar.onAge(e -> showByEdad());
        actionBar.onCountry(e -> showByPaisNombre());

        seed();
        showOriginal();
    }

    private void onAdd() {
        PlayerFormDialog dlg = new PlayerFormDialog(this);
        dlg.setVisible(true);
        dlg.getValues().ifPresent(vals -> {
            String nombre = vals[0];
            String pais   = vals[1];
            int edad      = Integer.parseInt(vals[2]);
            int puntaje   = Integer.parseInt(vals[3]);

            boolean ok = repo.add(new TennisPlayer(nombre, pais, edad, puntaje));
            if (!ok) JOptionPane.showMessageDialog(this, "Ya hay 15 tenistas. No se pueden agregar más.");
            showOriginal();
        });
    }

    private void showOriginal() {
        TennisPlayer[] arr = repo.asArrayOriginal();
        playersTable.setRows(TennisRepo.toMatrix(arr));
    }

    private void showByPuntaje() {
        TennisPlayer[] arr = repo.asArrayOriginal();
        TennisRepo.insertionSortPuntajeDesc(arr);
        playersTable.setRows(TennisRepo.toMatrix(arr));
    }

    private void showByEdad() {
        TennisPlayer[] arr = repo.asArrayOriginal();
        TennisRepo.quickSortEdadAsc(arr);
        playersTable.setRows(TennisRepo.toMatrix(arr));
    }

    private void showByPaisNombre() {
        TennisPlayer[] arr = repo.asArrayOriginal();
        TennisRepo.mergeSortPaisNombreAsc(arr);
        playersTable.setRows(TennisRepo.toMatrix(arr));
    }

    private void seed() {
        repo.add(new TennisPlayer("Novak Djokovic", "Serbia", 37, 12000));
        repo.add(new TennisPlayer("Carlos Alcaraz", "España", 22, 10150));
        repo.add(new TennisPlayer("Jannik Sinner", "Italia", 24, 9800));
        repo.add(new TennisPlayer("Daniil Medvedev", "Rusia", 29, 8500));
        repo.add(new TennisPlayer("Alexander Zverev", "Alemania", 28, 7900));
        repo.add(new TennisPlayer("Alex de Miñaur", "Australia", 26, 3935));
    }

    void main() {
        SwingUtilities.invokeLater(() -> new TennisAppFrame().setVisible(true));
    }
}
