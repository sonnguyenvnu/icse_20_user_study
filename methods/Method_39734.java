public static void storeMethodArgumentFromObject(final MethodVisitor mv,final MethodInfo methodInfo,final int index){
  int type=methodInfo.getArgument(index).getOpcode();
  int offset=methodInfo.getArgumentOffset(index);
  storeValue(mv,offset,type);
}
