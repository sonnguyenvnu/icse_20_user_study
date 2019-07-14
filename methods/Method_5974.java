/** 
 * Sorts the samples by value.
 */
private void ensureSortedByValue(){
  if (currentSortOrder != SORT_ORDER_BY_VALUE) {
    Collections.sort(samples,VALUE_COMPARATOR);
    currentSortOrder=SORT_ORDER_BY_VALUE;
  }
}
