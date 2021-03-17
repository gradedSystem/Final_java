package kz.edu.astanait.interfaces;

import java.util.List;

public interface IController<T> {
    void add(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> getAll();
    T getById(int id);
}
