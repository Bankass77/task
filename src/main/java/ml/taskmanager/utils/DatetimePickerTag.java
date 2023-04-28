package ml.taskmanager.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class DatetimePickerTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Date value;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String valueStr = (value != null) ? formatter.format(value) : "";
			out.write("<input type=\"datetime-local\" id=\"" + id + "\" name=\"" + name + "\" value=\"" + valueStr
					+ "\">");
			return Tag.SKIP_BODY;
		} catch (IOException e) {
			throw new JspException("Error: " + e.getMessage());
		}
	}
}