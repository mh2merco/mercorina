package service;

import dao.EtudiantDAO;

public class EtudiantService {
    private final EtudiantDAO etudiantDAO;

    public EtudiantService() {
        this.etudiantDAO = new EtudiantDAO();
    }

    public EtudiantDAO getEtudiantDAO() {
        return etudiantDAO;
    }
}
