package com.tms.servlet;

import com.tms.domain.Actor;
import com.tms.service.ActorCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/actor")
public class ActorCrudServlet extends HttpServlet {
    ActorCrudService actorCrudService = new ActorCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestActorId = Integer.parseInt(req.getParameter("id"));
        Actor actor = actorCrudService.getActorById(requestActorId);
        req.setAttribute("actor", actor);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/singleActor.jsp").forward(req, resp);
    }

}
