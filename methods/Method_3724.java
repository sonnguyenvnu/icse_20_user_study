@Override public UpdateOp obtainUpdateOp(int cmd,int positionStart,int itemCount,Object payload){
  UpdateOp op=mUpdateOpPool.acquire();
  if (op == null) {
    op=new UpdateOp(cmd,positionStart,itemCount,payload);
  }
 else {
    op.cmd=cmd;
    op.positionStart=positionStart;
    op.itemCount=itemCount;
    op.payload=payload;
  }
  return op;
}
