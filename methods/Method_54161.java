/** 
 * Array version of:  {@link #aiGetMaterialIntegerArray GetMaterialIntegerArray} 
 */
@NativeType("aiReturn") public static int aiGetMaterialIntegerArray(@NativeType("struct aiMaterial const *") AIMaterial pMat,@NativeType("char const *") CharSequence pKey,@NativeType("unsigned int") int type,@NativeType("unsigned int") int index,@NativeType("int *") int[] pOut,@Nullable @NativeType("unsigned int *") int[] pMax){
  long __functionAddress=Functions.GetMaterialIntegerArray;
  if (CHECKS) {
    checkSafe(pMax,1);
    check(pOut,pMax[0]);
    AIMaterial.validate(pMat.address());
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(pKey,true);
    long pKeyEncoded=stack.getPointerAddress();
    return invokePPPPI(pMat.address(),pKeyEncoded,type,index,pOut,pMax,__functionAddress);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
