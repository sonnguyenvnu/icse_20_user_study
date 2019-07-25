public static AtomicNumericFieldData empty(final int maxDoc){
  return new AtomicDoubleFieldData(0){
    @Override public SortedNumericDoubleValues getDoubleValues(){
      return FieldData.emptySortedNumericDoubles();
    }
    @Override public Collection<Accountable> getChildResources(){
      return Collections.emptyList();
    }
  }
;
}
