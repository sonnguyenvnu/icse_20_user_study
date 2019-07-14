@Override public void process(ResultItems resultItems,Task task){
  Map<String,Object> resultItemsAll=resultItems.getAll();
  Iterator<Map.Entry<String,Object>> iterator=resultItemsAll.entrySet().iterator();
  while (iterator.hasNext()) {
    handleObject(iterator);
  }
}
