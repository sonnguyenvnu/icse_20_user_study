@Override public BiConsumer<ObservableList<T>,T> accumulator(){
  return (alreadyProcessed,newItem) -> {
    Optional<T> sameItemInTree=alreadyProcessed.stream().filter(item -> equivalence.test(item,newItem)).findFirst();
    if (sameItemInTree.isPresent()) {
      for (      T child : new ArrayList<>(getChildren.apply(newItem))) {
        merge(sameItemInTree.get(),child);
      }
    }
 else {
      alreadyProcessed.add(newItem);
    }
  }
;
}
