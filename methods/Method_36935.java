/** 
 * @since 3.0.0
 */
public Consumer<ReplaceGroupOp> asReplaceGroupConsumer(){
  return new Consumer<ReplaceGroupOp>(){
    @Override public void accept(    ReplaceGroupOp op) throws Exception {
      replace(op.getArg1(),op.getArg2());
    }
  }
;
}
