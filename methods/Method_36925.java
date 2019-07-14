/** 
 * @since 3.0.0
 */
public Consumer<InsertGroupOp> asInsertGroupConsumer(){
  return new Consumer<InsertGroupOp>(){
    @Override public void accept(    InsertGroupOp op) throws Exception {
      insertBatchWith(op.getArg1(),op.getArg2());
    }
  }
;
}
