/** 
 * Listening method. Synchronizes the shared  {@link MetaData} and applies them locally.
 * @param event
 */
@Subscribe public void listen(MetaDataChangedEvent event){
  if (checkCurrentConnection()) {
    synchronizeSharedMetaData(event.getMetaData(),globalCiteKeyPattern);
    synchronizeLocalDatabase();
    applyMetaData();
    dbmsProcessor.notifyClients();
  }
}
