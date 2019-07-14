private String urlFor(Class<? extends AdminTask> taskClass){
  RequestSpec requestSpec=adminRoutes.requestSpecForTask(taskClass);
  checkNotNull(requestSpec,"No admin task URL is registered for " + taskClass.getSimpleName());
  return String.format(ADMIN_URL_PREFIX + requestSpec.path(),scheme,host,port,urlPathPrefix);
}
