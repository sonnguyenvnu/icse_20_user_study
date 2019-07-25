/** 
 * Listening method. Updates an existing shared  {@link BibEntry}.
 * @param event {@link FieldChangedEvent} object
 */
@Subscribe public void listen(FieldChangedEvent event){
  if (isPresentLocalBibEntry(event.getBibEntry()) && isEventSourceAccepted(event) && checkCurrentConnection()) {
    synchronizeLocalMetaData();
    BibEntry bibEntry=event.getBibEntry();
    synchronizeSharedEntry(bibEntry);
    synchronizeLocalDatabase();
  }
}
