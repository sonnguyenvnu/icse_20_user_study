/** 
 * Listening method. Inserts a new  {@link BibEntry} into shared database.
 * @param event {@link EntryAddedEvent} object
 */
@Subscribe public void listen(EntryAddedEvent event){
  if (isEventSourceAccepted(event) && checkCurrentConnection()) {
    synchronizeLocalMetaData();
    synchronizeLocalDatabase();
    dbmsProcessor.insertEntry(event.getBibEntry());
  }
}
