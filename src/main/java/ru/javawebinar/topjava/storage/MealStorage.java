package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import java.util.List;

public interface MealStorage {
    Meal get(Integer id);
    List<Meal> getAll();
    void save(Meal meal);
    void delete(Integer id);
}
