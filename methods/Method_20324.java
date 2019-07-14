private void addNewOperation(@Type int type,int position,int itemCount,@Nullable EpoxyModel<?> payload){
  lastOp=UpdateOp.instance(type,position,itemCount,payload);
  opList.add(lastOp);
}
