/** 
 * Assigns the element at  {@code index} in {@code array} to {@code target}.
 */
public void aget(Local<?> target,Local<?> array,Local<Integer> index){
  addInstruction(new ThrowingInsn(Rops.opAget(target.type.ropType),sourcePosition,RegisterSpecList.make(array.spec(),index.spec()),catches));
  moveResult(target,true);
}
