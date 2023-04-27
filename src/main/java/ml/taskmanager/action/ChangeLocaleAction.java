package ml.taskmanager.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChangeLocaleAction extends Action {

	public ActionForward ActionForward(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String language = request.getParameter("language");
		String country = request.getParameter("country");
	
		if (null == country) {
			setLocale(request, new Locale(language));
		} else {
			setLocale(request, new Locale(language, country));
		}
		return new ActionForward(mapping.findForward("success"));
	}

}
