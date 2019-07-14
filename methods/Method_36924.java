/** 
 * @since 3.0.0
 */
public Consumer<InsertCellsOp> asInsertCellsConsumer(){
  return new Consumer<InsertCellsOp>(){
    @Override public void accept(    InsertCellsOp op) throws Exception {
      insertWith(op.getArg1(),op.getArg2());
    }
  }
;
}
