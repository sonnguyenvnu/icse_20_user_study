private void adapt(long key){
  indicator.record(key);
  if (indicator.getSample() == period) {
    maximumFilterSize=(int)(maximumSize * indicator.getIndicator());
    if (maximumFilterSize <= 0) {
      maximumFilterSize=1;
    }
    if (maximumFilterSize >= maximumSize) {
      maximumFilterSize=maximumSize - 1;
    }
    maximumMainResidentSize=maximumSize - maximumFilterSize;
    indicator.reset();
  }
}
