public Map<String,PropertyChange> refresh(){
  Map<String,PropertyChange> changes=new HashMap<>();
  populateChanges(changes,true);
  load();
  populateChanges(changes,false);
  return changes.values().stream().filter(propertyChange -> propertyChange.changed()).collect(Collectors.toMap(change -> change.getPropertyName(),Function.identity()));
}
