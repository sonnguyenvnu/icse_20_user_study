package org.nutz.mvc.impl.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.mvc.SessionProvider;

/**
 * 抽象的SessionProvider实现,�?�以作为
 * 
 * @author wendal
 * 
 */
public abstract class AbstractSessionProvider implements SessionProvider {

	private static final Object lock = new Object();

	public HttpServletRequest filter(final HttpServletRequest req,
									 final HttpServletResponse resp,
									 final ServletContext servletContext) {
		return new SessionProviderHttpServletRequestWrapper(req, resp, servletContext);
	}

	/**
	 * �?类覆盖此方法,以创建一个新的Session对象,或者任何你想�?�的事
	 */
	public abstract HttpSession createSession(final HttpServletRequest req,
											  final HttpServletResponse resp,
											  final ServletContext servletContext);
	
	public abstract HttpSession getExistSession(final HttpServletRequest req,
                                       final HttpServletResponse resp,
                                       final ServletContext servletContext);

	public void notifyStop() {}

	public class SessionProviderHttpServletRequestWrapper extends HttpServletRequestWrapper {

		protected HttpSession session;

		protected HttpServletRequest req;
		protected HttpServletResponse resp;
		protected ServletContext servletContext;

		public SessionProviderHttpServletRequestWrapper(HttpServletRequest req,
														HttpServletResponse resp,
														ServletContext servletContext) {
			super(req);
			this.req = req;
			this.resp = resp;
			this.servletContext = servletContext;
			this.session = getExistSession(req, resp, servletContext);
		}

		public HttpSession getSession(boolean create) {
			if (create && session == null) {
				synchronized (lock) {// 因为创建Session并�?需�?太多并�?�
					if (session == null)
						session = createSession(req, resp, servletContext);
				}
			}
			return session;
		}

		public HttpSession getSession() {
			return getSession(true);
		}
	}
}
