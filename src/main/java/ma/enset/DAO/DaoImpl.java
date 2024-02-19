package ma.enset.DAO;

import org.springframework.stereotype.Component;

@Component("Dao")
public class DaoImpl implements IDao{
    @Override
    public double getDate() {
        return 5;
    }
}
