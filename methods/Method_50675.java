/** 
 * This is temporary workaround to bypass the validation stage of the compiler while *still* doing the additional_validate stage. We are bypassing the validation stage because it does a deep validation that we don't have all the parts for yet in the offline compiler. Rather than stop all work on that, we bypass it so that we can still do useful things like find all your types, find all your methods, etc.
 */
@SuppressWarnings("unchecked") private void callAdditionalPassVisitor(ApexCompiler compiler){
  try {
    List<CodeUnit> allUnits=(List<CodeUnit>)FieldUtils.readDeclaredField(compiler,"allUnits",true);
    CompilerContext compilerContext=(CompilerContext)FieldUtils.readDeclaredField(compiler,"compilerContext",true);
    for (    CodeUnit unit : allUnits) {
      Method getOperation=CompilerStage.ADDITIONAL_VALIDATE.getDeclaringClass().getDeclaredMethod("getOperation");
      getOperation.setAccessible(true);
      CompilerOperation operation=(CompilerOperation)getOperation.invoke(CompilerStage.ADDITIONAL_VALIDATE);
      operation.invoke(compilerContext,unit);
    }
  }
 catch (  IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
    throw new RuntimeException(e);
  }
}
