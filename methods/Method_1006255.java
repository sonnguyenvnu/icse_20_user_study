@Subscribe public void listen(FieldChangedEvent fieldChangedEvent){
  if (fieldChangedEvent.getFieldName().equals(BibEntry.KEY_FIELD)) {
    removeKeyFromSet(fieldChangedEvent.getOldValue());
    addKeyToSet(fieldChangedEvent.getNewValue());
  }
}
