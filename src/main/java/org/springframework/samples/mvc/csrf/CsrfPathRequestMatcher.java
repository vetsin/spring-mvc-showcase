package org.springframework.samples.mvc.csrf;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.RequestMatcher;

public class CsrfPathRequestMatcher implements RequestMatcher 
{
	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

	private final Pattern pattern;

	public CsrfPathRequestMatcher(String pattern) {
		this(pattern, false);
	}

	public CsrfPathRequestMatcher(String pattern, boolean caseInsensitive) {
		if (caseInsensitive) {
			this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		} else {
			this.pattern = Pattern.compile(pattern);
		}
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		if (allowedMethods.matcher(request.getMethod()).matches())
			return false;

		String url = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String query = request.getQueryString();

        if (pathInfo != null || query != null) {
            StringBuilder sb = new StringBuilder(url);

            if (pathInfo != null) {
                sb.append(pathInfo);
            }

            if (query != null) {
                sb.append('?').append(query);
            }
            url = sb.toString();
        }
        
        return pattern.matcher(url).matches();
	}

}
