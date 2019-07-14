private static Change acquireMoveChange(int index,int toIndex,@Nullable Object data){
  final List<?> singleDataList=data != null ? Collections.singletonList(data) : null;
  return acquire(MOVE,index,toIndex,1,null,null,singleDataList,singleDataList);
}
