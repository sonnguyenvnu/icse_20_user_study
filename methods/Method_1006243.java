/** 
 * Listening method. Deletes the given  {@link BibEntry} from shared database.
 * @param event {@link EntryRemovedEvent} object
 */
@Subscribe public void listen(EntryRemovedEvent event){
  if (isEventSourceAccepted(event) && checkCurrentConnection()) {
    dbmsProcessor.removeEntry(event.getBibEntry());
    synchronizeLocalMetaData();
    synchronizeLocalDatabase();
  }
}
