/** 
 * Make engine as a consumer to accept cell'data change
 * @return
 * @since 3.0.0
 */
public Consumer<UpdateCellOp> asUpdateCellConsumer(){
  return new Consumer<UpdateCellOp>(){
    @Override public void accept(    UpdateCellOp op) throws Exception {
      update(op.getArg1());
    }
  }
;
}
