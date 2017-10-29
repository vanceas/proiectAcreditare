package com.proiectAcreditare;

import java.sql.SQLException;
import java.sql.Statement;

public class Material {
    int id;
    String material;
    String cumparator;
    int nrColi;

    public Material(int id, String material, String cumparator, int nrColi) {
        this.id = id;
        this.material = material;
        this.cumparator = cumparator;
        this.nrColi = nrColi;
    }

    public void saveMaterial(Statement myStmt) {
        String insertDb = "insert into depozit"
                + "(id,material,cumparator,nrColi)"
                + "values ("
                + this.id + ", '" + this.material + "','" +
                this.cumparator + "'," + this.nrColi + ")";
        try {
            myStmt.executeUpdate(insertDb);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

