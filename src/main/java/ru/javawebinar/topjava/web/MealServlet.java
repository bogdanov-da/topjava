package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.storage.MemoryMealStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealStorage mealStorage;

    @Override
    public void init() {
        mealStorage = new MemoryMealStorage();
        log.info("init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action == null ? "" : action) {
            case "delete":
                String id = req.getParameter("id");
                mealStorage.delete(parseId(id));
                log.info("Meal {} deleted", id);
                resp.sendRedirect("meals");
                break;
            case "create":
            case "edit":
                id = req.getParameter("id");
                Meal meal = "create".equals(action) ? new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                        "", 0) : mealStorage.get(parseId(id));
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/meal.jsp").forward(req, resp);
                log.info("Meal {} {}", id == null ? "new" : id, action);
                break;
            default:
                req.setAttribute("meals", MealsUtil.filteredByStreams(mealStorage.getAll(), LocalTime.MIN,
                        LocalTime.MAX, CALORIES_PER_DAY));
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
                log.info("Get all meals");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        String id = req.getParameter("id");
        mealStorage.save(new Meal(id == null || id.isEmpty() ? null : parseId(id), dateTime, description, calories));
        log.info("Post request for meal {}", id);
        resp.sendRedirect("meals");
    }

    private int parseId(String id) {
        return Integer.parseInt(id);
    }
}
