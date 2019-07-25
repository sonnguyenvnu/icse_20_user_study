private void populate(Map<Map<Property<?>,Object>,BlockState> stateMap){
  final Table<Property<?>,Object,BlockState> states=HashBasedTable.create();
  for (  final Map.Entry<Property<?>,Object> entry : this.values.entrySet()) {
    final Property<Object> property=(Property<Object>)entry.getKey();
    property.getValues().forEach(value -> {
      if (value != entry.getValue()) {
        BlockState modifiedState=stateMap.get(this.withValue(property,value));
        if (modifiedState != null) {
          states.put(property,value,modifiedState);
        }
 else {
          System.out.println(stateMap);
          WorldEdit.logger.warn("Found a null state at " + this.withValue(property,value));
        }
      }
    }
);
  }
  this.states=states.isEmpty() ? states : ArrayTable.create(states);
}
