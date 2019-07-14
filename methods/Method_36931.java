/** 
 * @since 3.0.0
 */
protected Consumer<RemoveGroupIdxOp> asRemoveGroupIdxConsumer(){
  return new Consumer<RemoveGroupIdxOp>(){
    @Override public void accept(    RemoveGroupIdxOp op) throws Exception {
      removeBatchBy(op.getArg1());
    }
  }
;
}
