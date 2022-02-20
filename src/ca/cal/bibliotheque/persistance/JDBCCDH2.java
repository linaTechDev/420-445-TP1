package ca.cal.bibliotheque.persistance;

import ca.cal.bibliotheque.model.CD;
import ca.cal.bibliotheque.model.Documents;
import ca.cal.bibliotheque.persistance.CRUD.JDBCBibliotheque;
import ca.cal.bibliotheque.persistance.CRUD.JDBCDocumentsH2;
import ca.cal.bibliotheque.persistance.DB.JDBCConfig;
import ca.cal.bibliotheque.persistance.DB.JDBCException;

import java.sql.*;

public class JDBCCDH2 implements JDBCCD {
    public void enregistrer(CD cd) {
        new JDBCDocumentsH2().enregistrer(cd.getDocument());
        JDBCBibliotheque.enregistrer("INSERT INTO CD VALUES (" +
                cd.getId() +
                ",'" + cd.getGenreMusique() +
                "','" + cd.getCompositeur() +
                "','" + cd.getInterprete() +
                "'," + cd.getDocument().getId() + ");");
    }

    public CD getCD(long cdId) {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(JDBCConfig.getDbUrl(),JDBCConfig.getUSER(),JDBCConfig.getPASS());
            PreparedStatement ps = conn.prepareStatement("SELECT * from CD WHERE id = ?");) {

            ps.setLong(1, cdId);

            Documents documents = new JDBCDocumentsH2().getDocuments(cdId);

            // NOTEZ le try à l'intérieur du try
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                return new CD(
                        documents,
                        rs.getString("genreMusique"),
                        rs.getString("compositeur"),
                        rs.getString("interprete")
                );
            }
        } catch (SQLException e) {
            JDBCException.handleException(e);
            return null;
        }
    }
}
