/** 
 * Make engine as a consumer to accept data to append at the end of list
 * @return
 * @since 3.0.0
 */
public Consumer<AppendGroupsOp> asAppendGroupsConsumer(){
  return new Consumer<AppendGroupsOp>(){
    @Override public void accept(    AppendGroupsOp op) throws Exception {
      appendBatchWith(op.getArg1());
    }
  }
;
}
