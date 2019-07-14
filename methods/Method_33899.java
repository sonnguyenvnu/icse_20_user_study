private void registerProxyFilter(ServletContext servletContext,String name){
  DelegatingFilterProxy filter=new DelegatingFilterProxy(name);
  filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
  servletContext.addFilter(name,filter).addMappingForUrlPatterns(null,false,"/*");
}
