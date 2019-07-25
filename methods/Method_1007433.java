public static void reload(OwbHotswapContext ctx){
  Set<Contextual<Object>> beans=ctx.$$ha$getBeansToReloadOwb();
  if (beans != null && !beans.isEmpty()) {
    LOGGER.debug("Starting re-loading {} beans in context '{}'",beans.size(),ctx);
    Iterator<Contextual<Object>> it=beans.iterator();
    while (it.hasNext()) {
      Contextual<Object> managedBean=it.next();
      destroy(ctx,managedBean);
    }
    beans.clear();
    LOGGER.debug("Finished re-loading beans in context '{}'",ctx);
  }
}
