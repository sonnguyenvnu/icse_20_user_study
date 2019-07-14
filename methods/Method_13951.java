@Override public void scrutinize(Statement statement,EntityIdValue entityId,boolean added){
  if (!added) {
    return;
  }
  Value mainSnakValue=statement.getClaim().getMainSnak().getValue();
  if (ItemIdValue.class.isInstance(mainSnakValue)) {
    PropertyIdValue pid=statement.getClaim().getMainSnak().getPropertyId();
    PropertyIdValue inversePid=getInverseConstraint(pid);
    if (inversePid != null) {
      EntityIdValue targetEntityId=(EntityIdValue)mainSnakValue;
      Set<EntityIdValue> currentValues=_statements.get(pid).get(entityId);
      if (currentValues == null) {
        currentValues=new HashSet<EntityIdValue>();
        _statements.get(pid).put(entityId,currentValues);
      }
      currentValues.add(targetEntityId);
    }
  }
}
