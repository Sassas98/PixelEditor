package org.example.model.persistence;

import java.util.List;

public interface Context<T> extends Disposable {
    /**
     * Save a t object
     * @param t object to save
     */
    public void save(T t);

    /**
     * Update a t object
     * @param t object to update
     */
    public void update(T t);

    /**
     * loader of a single object
     * @param id identifier
     * @return object with tath id
     */
    public T load(long id);

    /**
     * loader of all the object
     * @return all the object
     */
    public List<T> load();
}
