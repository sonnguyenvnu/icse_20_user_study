private void saveBatchParameter(final String name,final int size){
  if (batchParams == null) {
    batchParams=new HashMap<>();
  }
  batchParams.put(name,Integer.valueOf(size));
}
