/** 
 * @since 3.0.0
 */
protected Consumer<RemoveCellOp> asRemoveCellConsumer(){
  return new Consumer<RemoveCellOp>(){
    @Override public void accept(    RemoveCellOp op) throws Exception {
      removeBy(op.getArg1());
    }
  }
;
}
