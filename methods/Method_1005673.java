/** 
 * Compare ints or references. If the comparison is true, execution jumps to {@code trueLabel}. If it is false, execution continues to the next instruction.
 */
public <T>void compare(Comparison comparison,Label trueLabel,Local<T> a,Local<T> b){
  adopt(trueLabel);
  Rop rop=comparison.rop(StdTypeList.make(a.type.ropType,b.type.ropType));
  addInstruction(new PlainInsn(rop,sourcePosition,null,RegisterSpecList.make(a.spec(),b.spec())),trueLabel);
}
