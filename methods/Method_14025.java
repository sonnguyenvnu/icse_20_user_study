/** 
 * Separates out the statements which refer to new items from the rest of the update. The resulting updates are stored in  {@link referencingUpdates} and{@link updatesWithoutReferences}.
 * @param update
 * @throws ImpossibleSchedulingException if two new item ids are referred to in the same statement
 */
protected void splitUpdate(ItemUpdate update) throws ImpossibleSchedulingException {
  ItemUpdateBuilder remainingUpdateBuilder=new ItemUpdateBuilder(update.getItemId()).addLabels(update.getLabels(),true).addLabels(update.getLabelsIfNew(),false).addDescriptions(update.getDescriptions(),true).addDescriptions(update.getDescriptionsIfNew(),false).addAliases(update.getAliases()).deleteStatements(update.getDeletedStatements());
  Map<ItemIdValue,ItemUpdateBuilder> referencingUpdates=new HashMap<>();
  for (  Statement statement : update.getAddedStatements()) {
    Set<ReconItemIdValue> pointers=extractor.extractPointers(statement);
    if (pointers.isEmpty()) {
      remainingUpdateBuilder.addStatement(statement);
    }
 else     if (pointers.size() == 1 && !update.isNew()) {
      ItemIdValue pointer=pointers.stream().findFirst().get();
      ItemUpdateBuilder referencingBuilder=referencingUpdates.get(pointer);
      if (referencingBuilder == null) {
        referencingBuilder=new ItemUpdateBuilder(update.getItemId());
      }
      referencingBuilder.addStatement(statement);
      referencingUpdates.put(pointer,referencingBuilder);
    }
 else     if (pointers.size() == 1 && pointers.stream().findFirst().get().equals(update.getItemId())) {
      remainingUpdateBuilder.addStatement(statement);
    }
 else {
      throw new ImpossibleSchedulingException();
    }
  }
  ItemUpdate pointerFree=remainingUpdateBuilder.build();
  if (!pointerFree.isNull()) {
    pointerFreeUpdates.add(pointerFree);
  }
  for (  Entry<ItemIdValue,ItemUpdateBuilder> entry : referencingUpdates.entrySet()) {
    ItemUpdate pointerUpdate=entry.getValue().build();
    UpdateSequence pointerUpdatesForKey=pointerUpdates.get(entry.getKey());
    if (pointerUpdatesForKey == null) {
      pointerUpdatesForKey=new UpdateSequence();
    }
    pointerUpdatesForKey.add(pointerUpdate);
    pointerUpdates.put(entry.getKey(),pointerUpdatesForKey);
  }
}
