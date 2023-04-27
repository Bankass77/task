package ml.taskmanager.action;

import javax.servlet.http.HttpServletRequest;

public interface ServletRequestAware {
	public void setServletRequest(HttpServletRequest request);
}
