private void filterDuplicatesIfNeeded(List<EpoxyModel<?>> models){
  if (!filterDuplicates) {
    return;
  }
  timer.start("Duplicates filtered");
  Set<Long> modelIds=new HashSet<>(models.size());
  ListIterator<EpoxyModel<?>> modelIterator=models.listIterator();
  while (modelIterator.hasNext()) {
    EpoxyModel<?> model=modelIterator.next();
    if (!modelIds.add(model.id())) {
      int indexOfDuplicate=modelIterator.previousIndex();
      modelIterator.remove();
      int indexOfOriginal=findPositionOfDuplicate(models,model);
      EpoxyModel<?> originalModel=models.get(indexOfOriginal);
      if (indexOfDuplicate <= indexOfOriginal) {
        indexOfOriginal++;
      }
      onExceptionSwallowed(new IllegalEpoxyUsage("Two models have the same ID. ID's must be unique!" + "\nOriginal has position " + indexOfOriginal + ":\n" + originalModel + "\nDuplicate has position " + indexOfDuplicate + ":\n" + model));
    }
  }
  timer.stop();
}
