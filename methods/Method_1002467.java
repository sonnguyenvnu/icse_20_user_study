@Override public void store(DataMap parentMap,String key){
  DataList deleteList=(DataList)parentMap.get(OP_NAME);
  if (deleteList == null) {
    deleteList=new DataList();
    parentMap.put(OP_NAME,deleteList);
  }
  deleteList.add(key);
}
