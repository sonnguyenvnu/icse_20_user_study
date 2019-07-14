@Override public void scrutinize(Statement statement,EntityIdValue entityId,boolean added){
  scrutinize(statement.getClaim().getMainSnak(),entityId,added);
  scrutinizeSnakSet(statement.getClaim().getAllQualifiers(),entityId,added);
  for (  Reference ref : statement.getReferences()) {
    scrutinizeSnakSet(ref.getAllSnaks(),entityId,added);
  }
}
