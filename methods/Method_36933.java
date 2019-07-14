/** 
 * @since 3.0.0
 */
public Consumer<ReplaceCellOp> asReplaceCellConsumer(){
  return new Consumer<ReplaceCellOp>(){
    @Override public void accept(    ReplaceCellOp op) throws Exception {
      replace(op.getArg1(),op.getArg2());
    }
  }
;
}
