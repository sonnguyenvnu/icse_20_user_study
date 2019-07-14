/** 
 * Make engine as a consumer to accept data to append at the end of list
 * @return
 * @since 3.0.0
 */
public Consumer<AppendGroupOp> asAppendGroupConsumer(){
  return new Consumer<AppendGroupOp>(){
    @Override public void accept(    AppendGroupOp op) throws Exception {
      appendWith(op.getArg1());
    }
  }
;
}
