@Override public void onItemsUpdated(RecyclerView recyclerView,int positionStart,int itemCount,Object payload){
  handleUpdate(positionStart,itemCount,AdapterHelper.UpdateOp.UPDATE);
}
