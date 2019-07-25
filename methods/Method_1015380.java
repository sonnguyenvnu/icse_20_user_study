@Bean("shiroFilter") public ShiroFilterFactoryBean factory(@Qualifier("securityManager") SecurityManager securityManager){
  ShiroFilterFactoryBean factoryBean=new ShiroFilterFactoryBean();
  Map<String,Filter> filterMap=new HashMap<>();
  factoryBean.setSecurityManager(securityManager);
  filterMap.put("jwt",new JwtFilter());
  filterMap.put("resourceCheckFilter",new ResourceCheckFilter());
  factoryBean.setLoginUrl("/admin/login");
  factoryBean.setSuccessUrl("/index");
  factoryBean.setFilters(filterMap);
  factoryBean.setUnauthorizedUrl("/403");
  LinkedHashMap<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
  filterChainDefinitionMap.put("/favicon.ico","anon");
  filterChainDefinitionMap.put("/css/**","anon");
  filterChainDefinitionMap.put("/js/**","anon");
  filterChainDefinitionMap.put("/media/**","anon");
  filterChainDefinitionMap.put("/","anon");
  filterChainDefinitionMap.put("/**","jwt");
  factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
  return factoryBean;
}
