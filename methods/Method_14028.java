/** 
 * Splits an update into two parts
 * @param update
 */
protected void splitUpdate(ItemUpdate update){
  ItemUpdateBuilder pointerFreeBuilder=new ItemUpdateBuilder(update.getItemId()).addLabels(update.getLabels(),true).addLabels(update.getLabelsIfNew(),false).addDescriptions(update.getDescriptions(),true).addDescriptions(update.getDescriptionsIfNew(),false).addAliases(update.getAliases()).deleteStatements(update.getDeletedStatements());
  ItemUpdateBuilder pointerFullBuilder=new ItemUpdateBuilder(update.getItemId());
  for (  Statement statement : update.getAddedStatements()) {
    Set<ReconItemIdValue> pointers=extractor.extractPointers(statement);
    if (pointers.isEmpty()) {
      pointerFreeBuilder.addStatement(statement);
    }
 else {
      pointerFullBuilder.addStatement(statement);
    }
    allPointers.addAll(pointers);
  }
  if (update.isNew()) {
    ItemUpdate pointerFree=pointerFreeBuilder.build();
    if (!pointerFree.isNull()) {
      pointerFreeUpdates.add(pointerFree);
    }
    ItemUpdate pointerFull=pointerFullBuilder.build();
    if (!pointerFull.isEmpty()) {
      pointerFullUpdates.add(pointerFull);
    }
  }
 else {
    pointerFullUpdates.add(update);
  }
}
