@Override public void scrutinize(Statement statement,EntityIdValue entityId,boolean added){
  if (statement.getReferences().isEmpty() && added) {
    warning(type);
  }
}
