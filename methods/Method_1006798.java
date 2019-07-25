@Override public void remove(final Object obj){
  mainExecutor.submit(new Runnable(){
    @SuppressWarnings("rawtypes") @Override public void run(){
      Class clz=obj.getClass();
      filterTryToCreate(clz);
      ArrayList<Object> objectList=removeMap.get(clz);
      if (objectList == null) {
        objectList=new ArrayList<Object>();
        removeMap.put(clz,objectList);
      }
      if (!objectList.contains(obj)) {
        objectList.add(obj);
      }
    }
  }
);
}
