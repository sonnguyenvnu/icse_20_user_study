@Override public void onItemsMoved(RecyclerView recyclerView,int from,int to,int itemCount){
  handleUpdate(from,to,AdapterHelper.UpdateOp.MOVE);
}
