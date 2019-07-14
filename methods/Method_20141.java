private void notifyChanges(UpdateOpHelper opHelper){
  for (  UpdateOp op : opHelper.opList) {
switch (op.type) {
case UpdateOp.ADD:
      adapter.notifyItemRangeInserted(op.positionStart,op.itemCount);
    break;
case UpdateOp.MOVE:
  adapter.notifyItemMoved(op.positionStart,op.itemCount);
break;
case UpdateOp.REMOVE:
adapter.notifyItemRangeRemoved(op.positionStart,op.itemCount);
break;
case UpdateOp.UPDATE:
if (immutableModels && op.payloads != null) {
adapter.notifyItemRangeChanged(op.positionStart,op.itemCount,new DiffPayload(op.payloads));
}
 else {
adapter.notifyItemRangeChanged(op.positionStart,op.itemCount);
}
break;
default :
throw new IllegalArgumentException("Unknown type: " + op.type);
}
}
}
