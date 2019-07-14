public void deleteRange(int index,int count,@Nullable List<?> data){
  addChange(Change.removeRange(index,count,data));
}
