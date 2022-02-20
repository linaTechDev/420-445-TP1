package ca.cal.bibliotheque.persistance.CRUD;

import ca.cal.bibliotheque.model.Clients;
import ca.cal.bibliotheque.model.Employe;

public interface JDBCEmploye {
    void enregistrer(Employe employe);
    Employe getEmploye(long employeId);
    void modification(Employe employe);
}
