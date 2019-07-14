static FrescoController getController(ComponentContext context,@Nullable FrescoContext contextOverride){
  return resolveContext(context,contextOverride).getController();
}
