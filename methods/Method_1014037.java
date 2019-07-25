/** 
 * Handles the "restoreOnStartup" strategy for the item. If the item state is still undefined when entering this method, all persistence configurations are checked, if they have the "restoreOnStartup" strategy configured for the item. If so, the item state will be set to its last persisted value.
 * @param item the item to restore the state for
 */
@SuppressWarnings("null") private void initialize(Item item){
  if (item.getState().equals(UnDefType.NULL) && item instanceof GenericItem) {
    for (    Entry<String,PersistenceServiceConfiguration> entry : persistenceServiceConfigs.entrySet()) {
      final String serviceName=entry.getKey();
      final PersistenceServiceConfiguration config=entry.getValue();
      for (      SimpleItemConfiguration itemConfig : config.getConfigs()) {
        if (hasStrategy(serviceName,itemConfig,SimpleStrategy.Globals.RESTORE)) {
          if (appliesToItem(itemConfig,item)) {
            PersistenceService service=persistenceServices.get(serviceName);
            if (service instanceof QueryablePersistenceService) {
              QueryablePersistenceService queryService=(QueryablePersistenceService)service;
              FilterCriteria filter=new FilterCriteria().setItemName(item.getName()).setPageSize(1);
              Iterable<HistoricItem> result=safeCaller.create(queryService,QueryablePersistenceService.class).onTimeout(() -> {
                logger.warn("Querying persistence service '{}' takes more than {}ms.",queryService.getId(),SafeCaller.DEFAULT_TIMEOUT);
              }
).onException(e -> {
                logger.error("Exception occurred while querying persistence service '{}': {}",queryService.getId(),e.getMessage(),e);
              }
).build().query(filter);
              if (result != null) {
                Iterator<HistoricItem> it=result.iterator();
                if (it.hasNext()) {
                  HistoricItem historicItem=it.next();
                  GenericItem genericItem=(GenericItem)item;
                  genericItem.removeStateChangeListener(this);
                  genericItem.setState(historicItem.getState());
                  genericItem.addStateChangeListener(this);
                  logger.debug("Restored item state from '{}' for item '{}' -> '{}'",new Object[]{DateFormat.getDateTimeInstance().format(historicItem.getTimestamp()),item.getName(),historicItem.getState().toString()});
                  return;
                }
              }
            }
 else             if (service != null) {
              logger.warn("Failed to restore item states as persistence service '{}' can not be queried.",serviceName);
            }
          }
        }
      }
    }
  }
}
