package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealStorage {
    Meal get(int id);

    List<Meal> getAll();

    Meal save(Meal meal);

    void delete(int id);
}
