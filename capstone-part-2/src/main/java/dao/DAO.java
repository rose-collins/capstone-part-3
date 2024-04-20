package dao;

public interface DAO<T> {
	boolean create(T object);
    T read(String id);
    boolean update(T object);
    boolean delete(String id);
}
