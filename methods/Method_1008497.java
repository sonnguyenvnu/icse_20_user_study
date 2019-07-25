public static AtomicGeoPointFieldData empty(final int maxDoc){
  return new AbstractAtomicGeoPointFieldData(){
    @Override public long ramBytesUsed(){
      return 0;
    }
    @Override public Collection<Accountable> getChildResources(){
      return Collections.emptyList();
    }
    @Override public void close(){
    }
    @Override public MultiGeoPointValues getGeoPointValues(){
      return FieldData.emptyMultiGeoPoints();
    }
  }
;
}
