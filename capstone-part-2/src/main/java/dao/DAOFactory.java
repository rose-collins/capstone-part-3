package dao;

public class DAOFactory {
	//types of DAOs that this factory can create instances of
	public enum DAOType {
		//constant will be passed to getDAO method example - DAOType.USER
        USER
    }

	//will create and return instance of requested DAO based on DAOType provided
    public static DAO<?> getDAO(DAOType daoType) {
        switch (daoType) {
        	//if user was passed, new instance of UserDAO gets created
            case USER:
                return new UserDAO();
            default:
                throw new IllegalArgumentException("Invalid DAO type");
        }
    }
}
