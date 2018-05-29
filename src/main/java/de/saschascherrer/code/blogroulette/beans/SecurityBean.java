package de.saschascherrer.code.blogroulette.beans;

import java.security.Principal;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.dhbw.mosbach.ai.pfad.db.AppUserDao;
//import org.dhbw.mosbach.ai.pfad.model.AppUser;

@Named
@RequestScoped
public class SecurityBean {
//    private static final Logger logger = LoggerFactory.getLogger(SecurityBean.class);

//    @Inject
//    private AppUserDao appUserDao;

    /**
     * The currently logged in user.
     */
//    private AppUser loggedInUser;

    private HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    private Optional<Principal> getPrincipal() {
        return Optional.ofNullable(getRequest().getUserPrincipal());
    }

    public String getLoginName() {
        return getPrincipal().map(p -> p.getName()).orElse("");
    }

    public boolean isAuthenticated() {
        return getPrincipal().isPresent();
    }

    public boolean isUserInRole(String role) {
        return getRequest().isUserInRole(role);
    }

    /*
    public AppUser getUser() {
        final Optional<Principal> principal = getPrincipal();

        if (principal.isPresent()) {
            if ((loggedInUser == null) || !principal.get().getName().equals(loggedInUser.getLoginId())) {
                loggedInUser = appUserDao.findByUnique("loginId", principal.get().getName());
            }

            return loggedInUser;
        }

        return null;
    }
    */

    public String login() {
//        loggedInUser = null;
        return "/admin/index.xhtml?faces-redirect=true";
    }

    public String logout() {
        final HttpServletRequest request = getRequest();

//        loggedInUser = null;

        try {
            request.logout();
            request.getSession().invalidate();
        } catch (final ServletException e) {
//            logger.warn("Exception during logout", e);
        }

        return "login.xhtml?faces-redirect=true";
    }
}
