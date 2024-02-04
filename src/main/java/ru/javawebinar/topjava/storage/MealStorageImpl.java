package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.javawebinar.topjava.util.MealsUtil.MEALS;

public class MealStorageImpl implements MealStorage {
    private final AtomicInteger index = new AtomicInteger(0);
    private final Map<Integer, Meal> storage = new ConcurrentHashMap<>();

    public MealStorageImpl() {
        MEALS.forEach(this::save);
    }

    @Override
    public void save(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(index.incrementAndGet());
            storage.put(meal.getId(), meal);
            return;
        }
        storage.computeIfPresent(meal.getId(), (id, old) -> meal);
    }

    @Override
    public Meal get(Integer id) {
        return storage.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(Integer id) {
        storage.remove(id);
    }
}
