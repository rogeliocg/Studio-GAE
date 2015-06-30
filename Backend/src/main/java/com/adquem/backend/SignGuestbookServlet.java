package com.adquem.backend;

import com.adquem.backend.model.Greeting;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import static com.adquem.backend.OfyService.ofy;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Rogelio on 6/30/15.
 */
public class SignGuestbookServlet extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Greeting greeting;

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        String guestbookName = req.getParameter("guestbookName");
        String content = req.getParameter("content");
        if (user != null) {
            greeting = new Greeting(guestbookName, content, user.getUserId(), user.getEmail());
        } else {
            greeting = new Greeting(guestbookName, content);
        }

        // Use Objectify to save the greeting and now() is used to make the call synchronously as we
        // will immediately get a new page using redirect and we want the data to be present.
//        ObjectifyService.ofy().save().entity(greeting).now();
        ofy().save().entity(greeting).now();

        resp.sendRedirect("/guestbook.jsp?guestbookName=" + guestbookName);
    }
}
