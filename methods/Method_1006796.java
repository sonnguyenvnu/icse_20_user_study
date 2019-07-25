@Override public void create(final Object obj){
  mainExecutor.submit(new Runnable(){
    @SuppressWarnings("rawtypes") @Override public void run(){
      Class clz=obj.getClass();
      filterTryToCreate(clz);
      ArrayList<Object> objectList=creationMap.get(clz);
      if (objectList == null) {
        objectList=new ArrayList<Object>();
        creationMap.put(clz,objectList);
      }
      if (!objectList.contains(obj)) {
        objectList.add(obj);
      }
    }
  }
);
}
