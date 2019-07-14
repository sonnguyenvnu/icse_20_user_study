@Override public void scrutinize(ItemUpdate update){
  EntityIdValue currentEntityId=update.getItemId();
  for (  Statement statement : update.getAddedStatements()) {
    scrutinize(statement,currentEntityId,true);
  }
  for (  Statement statement : update.getDeletedStatements()) {
    scrutinize(statement,currentEntityId,false);
  }
}
