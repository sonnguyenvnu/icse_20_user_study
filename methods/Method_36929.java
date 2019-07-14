/** 
 * @since 3.0.0
 */
protected Consumer<RemoveCellPositionOp> asRemoveCellPositionConsumer(){
  return new Consumer<RemoveCellPositionOp>(){
    @Override public void accept(    RemoveCellPositionOp op) throws Exception {
      removeBy(op.getArg1());
    }
  }
;
}
