/** 
 * @since 3.0.0
 */
public Consumer<InsertGroupsOp> asInsertGroupsConsumer(){
  return new Consumer<InsertGroupsOp>(){
    @Override public void accept(    InsertGroupsOp op) throws Exception {
      insertBatchWith(op.getArg1(),op.getArg2());
    }
  }
;
}
