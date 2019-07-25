/** 
 * Transfers flow control to the instructions at  {@code target}. It is an error to jump to a label not marked on this  {@code Code}.
 */
public void jump(Label target){
  adopt(target);
  addInstruction(new PlainInsn(Rops.GOTO,sourcePosition,null,RegisterSpecList.EMPTY),target);
}
