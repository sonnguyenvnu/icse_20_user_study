private <D,R>void invoke(Rop rop,MethodId<D,R> method,Local<? super R> target,Local<? extends D> object,Local<?>... args){
  addInstruction(new ThrowingCstInsn(rop,sourcePosition,concatenate(object,args),catches,method.constant));
  if (target != null) {
    moveResult(target,false);
  }
}
