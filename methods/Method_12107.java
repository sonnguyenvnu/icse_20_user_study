@Bean public ServletRegistrationBean druidSverlet(){
  ServletRegistrationBean reg=new ServletRegistrationBean();
  reg.setServlet(new StatViewServlet());
  reg.addUrlMappings("/druid/*");
  reg.addInitParameter("loginUsername","joshua");
  reg.addInitParameter("loginPassword","123456");
  reg.addInitParameter("logSlowSql","true");
  reg.addInitParameter("slowSqlMillis","1000");
  return reg;
}
