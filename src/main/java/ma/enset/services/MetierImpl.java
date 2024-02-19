package ma.enset.services;

import ma.enset.DAO.IDao;

public class MetierImpl implements IMetier{
    private IDao dao;

    public IDao getDao() {
        return dao;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double newVal = dao.getDate();
        return newVal*2;
    }
}
