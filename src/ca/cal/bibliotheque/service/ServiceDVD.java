package ca.cal.bibliotheque.service;

import ca.cal.bibliotheque.model.DVD;
import ca.cal.bibliotheque.persistance.CRUD.JDBCDVDH2;

public class ServiceDVD {
    public void enregistrer(DVD dvd) {
        new JDBCDVDH2().enregistrer(dvd);
    }

    public DVD getDVD(long dvdId) {
        return new JDBCDVDH2().getDVD(dvdId);
    }
}
