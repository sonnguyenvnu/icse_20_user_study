@Override public void switchTo(int index){
  BusSupport busSupport=serviceManager.getService(BusSupport.class);
  if (busSupport != null) {
    storeCache();
    args.put(KEY_INDEX,String.valueOf(index));
    busSupport.post(BusSupport.obtainEvent("switchTo",null,args,null));
    mIndex=index;
  }
}
