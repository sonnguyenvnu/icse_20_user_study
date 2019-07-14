public ItemUpdate rewrite(ItemUpdate update){
  Set<MonolingualTextValue> labels=update.getLabels().stream().map(l -> copy(l)).collect(Collectors.toSet());
  Set<MonolingualTextValue> labelsIfNew=update.getLabelsIfNew().stream().map(l -> copy(l)).collect(Collectors.toSet());
  Set<MonolingualTextValue> descriptions=update.getDescriptions().stream().map(l -> copy(l)).collect(Collectors.toSet());
  Set<MonolingualTextValue> descriptionsIfNew=update.getDescriptionsIfNew().stream().map(l -> copy(l)).collect(Collectors.toSet());
  Set<MonolingualTextValue> aliases=update.getAliases().stream().map(l -> copy(l)).collect(Collectors.toSet());
  List<Statement> addedStatements=update.getAddedStatements().stream().map(l -> copy(l)).collect(Collectors.toList());
  Set<Statement> deletedStatements=update.getDeletedStatements().stream().map(l -> copy(l)).collect(Collectors.toSet());
  return new ItemUpdate(update.getItemId(),addedStatements,deletedStatements,labels,labelsIfNew,descriptions,descriptionsIfNew,aliases);
}
