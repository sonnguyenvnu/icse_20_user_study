@Override public void put(MfDate validDate,Object item){
  myContents.put(MfDate.today(),currentValidHistory().copy());
  currentValidHistory().put(validDate,item);
}
