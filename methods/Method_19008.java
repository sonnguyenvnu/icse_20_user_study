public void move(int fromIndex,int toIndex,@Nullable Object data){
  addChange(Change.move(fromIndex,toIndex,data));
}
