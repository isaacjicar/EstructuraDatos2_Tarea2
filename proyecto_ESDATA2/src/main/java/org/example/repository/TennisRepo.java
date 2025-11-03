package org.example.repository;

import org.example.model.TennisPlayer;

import java.util.Arrays;
import java.util.Comparator;

public class TennisRepo {
    private final TennisPlayer[] data = new TennisPlayer[15];
    private int size = 0;

    public boolean add(TennisPlayer p) {
        if (size >= 15) return false;
        data[size++] = p;
        return true;
    }


    public TennisPlayer[] asArrayOriginal() {
        return Arrays.copyOf(data, size);
    }


    public static Object[][] toMatrix(TennisPlayer[] arr) {
        Object[][] m = new Object[arr.length][4];
        for (int i = 0; i < arr.length; i++) {
            m[i][0] = arr[i].getNombre();
            m[i][1] = arr[i].getPais();
            m[i][2] = arr[i].getEdad();
            m[i][3] = arr[i].getPuntaje();
        }
        return m;
    }



//Ordenación por inserción
    public static void insertionSortPuntajeDesc(TennisPlayer[] a) {
        for (int i = 1; i < a.length; i++) {
            TennisPlayer key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j].getPuntaje() < key.getPuntaje()) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

//Quicksort
    public static void quickSortEdadAsc(TennisPlayer[] a) {
        quickEdad(a, 0, a.length - 1);
    }
    private static void quickEdad(TennisPlayer[] a, int lo, int hi) {
        if (lo >= hi) return;
        int p = a[(lo + hi) / 2].getEdad();
        int i = lo, j = hi;
        while (i <= j) {
            while (a[i].getEdad() < p) i++;
            while (a[j].getEdad() > p) j--;
            if (i <= j) {
                TennisPlayer t = a[i]; a[i] = a[j]; a[j] = t;
                i++; j--;
            }
        }
        if (lo < j) quickEdad(a, lo, j);
        if (i < hi) quickEdad(a, i, hi);
    }

//Mergesort
    public static void mergeSortPaisNombreAsc(TennisPlayer[] a) {
        Comparator<TennisPlayer> cmp = (x, y) -> {
            int c = x.getPais().compareToIgnoreCase(y.getPais());
            if (c != 0) return c;
            return x.getNombre().compareToIgnoreCase(y.getNombre());
        };
        TennisPlayer[] aux = new TennisPlayer[a.length];
        mergePaisNombre(a, aux, 0, a.length - 1, cmp);
    }
    private static void mergePaisNombre(TennisPlayer[] a, TennisPlayer[] aux, int lo, int hi, Comparator<TennisPlayer> cmp) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        mergePaisNombre(a, aux, lo, mid, cmp);
        mergePaisNombre(a, aux, mid + 1, hi, cmp);

        for (int k = lo; k <= hi; k++) aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (cmp.compare(aux[i], aux[j]) <= 0) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }
}
