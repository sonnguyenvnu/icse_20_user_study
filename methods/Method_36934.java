/** 
 * @since 3.0.0
 */
public Consumer<ReplaceGroupContentOp> asReplaceGroupContentConsumer(){
  return new Consumer<ReplaceGroupContentOp>(){
    @Override public void accept(    ReplaceGroupContentOp op) throws Exception {
      replace(op.getArg1(),op.getArg2());
    }
  }
;
}
