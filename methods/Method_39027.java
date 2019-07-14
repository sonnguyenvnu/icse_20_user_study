protected ActionFilter[] parseActionFilters(final Class<?> actionClass,final Method actionMethod,final ActionConfig actionConfig){
  Class<? extends ActionFilter>[] filterClasses=readActionFilters(actionMethod);
  if (filterClasses == null) {
    filterClasses=readActionFilters(actionClass);
  }
  if (filterClasses == null) {
    filterClasses=actionConfig.getFilters();
  }
  return filtersManager.resolveAll(filterClasses);
}
