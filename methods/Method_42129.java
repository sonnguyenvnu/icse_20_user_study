private static Comparator<Album> getBaseComparator(SortingOrder sortingOrder){
  return sortingOrder == SortingOrder.ASCENDING ? getPinned() : getReversedPinned();
}
