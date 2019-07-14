private Comparator<StubMapping> sortedByPriorityThenReverseInsertionOrder(){
  return new Comparator<StubMapping>(){
    public int compare(    StubMapping one,    StubMapping two){
      int priorityComparison=one.comparePriorityWith(two);
      if (priorityComparison != 0) {
        return priorityComparison;
      }
      return Long.compare(two.getInsertionIndex(),one.getInsertionIndex());
    }
  }
;
}
