@Subscribe public void listen(FieldChangedEvent event){
  if (event.getFieldName().equals(BibEntry.KEY_FIELD)) {
    String newKey=event.getNewValue();
    String oldKey=event.getOldValue();
    updateEntryLinks(newKey,oldKey);
  }
}
