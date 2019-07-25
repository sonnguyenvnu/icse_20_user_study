@Subscribe public synchronized void listen(@SuppressWarnings("unused") BibDatabaseContextChangedEvent event){
  if (!(event instanceof FieldChangedEvent)) {
    eventBus.post(event);
  }
 else {
    FieldChangedEvent fieldChange=(FieldChangedEvent)event;
    boolean isEditOnNewField=lastFieldChanged == null || !lastFieldChanged.equals(fieldChange.getFieldName());
    if (fieldChange.getDelta() > 1 || isEditOnNewField) {
      lastFieldChanged=fieldChange.getFieldName();
      eventBus.post(event);
    }
  }
}
