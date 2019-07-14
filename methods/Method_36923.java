/** 
 * @since 3.0.0
 */
public Consumer<InsertCellOp> asInsertCellConsumer(){
  return new Consumer<InsertCellOp>(){
    @Override public void accept(    InsertCellOp op) throws Exception {
      insertWith(op.getArg1(),op.getArg2());
    }
  }
;
}
