private boolean areSiblingsEqual(final int index){
  return COMPARATOR.compare(operations.get(index),operations.get(index + 1)) == 0;
}
