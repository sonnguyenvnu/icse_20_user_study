protected void initAdditionalRoutes(Router routeBuilder){
  for (  AdminApiExtension apiExtension : apiExtensions) {
    apiExtension.contributeAdminApiRoutes(routeBuilder);
  }
}
