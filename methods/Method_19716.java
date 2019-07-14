@Bean public FilterRegistrationBean timeFilter(){
  FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
  TimeFilter timeFilter=new TimeFilter();
  filterRegistrationBean.setFilter(timeFilter);
  List<String> urlList=new ArrayList<>();
  urlList.add("/*");
  filterRegistrationBean.setUrlPatterns(urlList);
  return filterRegistrationBean;
}
