@Override public void onItemsRemoved(RecyclerView recyclerView,int positionStart,int itemCount){
  handleUpdate(positionStart,itemCount,AdapterHelper.UpdateOp.REMOVE);
}
