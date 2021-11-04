package hu.alkfejl.dao;

import hu.alkfejl.model.Eredmeny;

import java.util.List;

public interface EredmenyDAO {

    List<Eredmeny> findALl();

    boolean save(Eredmeny eredmeny);

    int findFirst();
}
