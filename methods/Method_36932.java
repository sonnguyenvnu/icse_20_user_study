/** 
 * @since 3.0.0
 */
protected Consumer<RemoveGroupOp> asRemoveGroupConsumer(){
  return new Consumer<RemoveGroupOp>(){
    @Override public void accept(    RemoveGroupOp op) throws Exception {
      removeBatchBy(op.getArg1());
    }
  }
;
}
