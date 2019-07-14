public AdminRequestHandler buildAdminRequestHandler(){
  AdminRoutes adminRoutes=AdminRoutes.defaultsPlus(options.extensionsOfType(AdminApiExtension.class).values(),options.getNotMatchedRenderer());
  return new AdminRequestHandler(adminRoutes,this,new BasicResponseRenderer(),options.getAdminAuthenticator(),options.getHttpsRequiredForAdminApi(),getAdminRequestFilters());
}
