/** 
 * Returns the inferred method type of the template based on the given actual argument types.
 * @throws InferException if no instances of the specified type variables would allow the {@code actualArgTypes} to match the {@code expectedArgTypes}
 */
private Type infer(Warner warner,Inliner inliner,List<Type> freeTypeVariables,List<Type> expectedArgTypes,Type returnType,List<Type> actualArgTypes) throws InferException {
  Symtab symtab=inliner.symtab();
  Type methodType=new MethodType(expectedArgTypes,returnType,List.<Type>nil(),symtab.methodClass);
  if (!freeTypeVariables.isEmpty()) {
    methodType=new ForAll(freeTypeVariables,methodType);
  }
  Enter enter=inliner.enter();
  MethodSymbol methodSymbol=new MethodSymbol(0,inliner.asName("__m__"),methodType,symtab.unknownSymbol);
  Type site=symtab.methodClass.type;
  Env<AttrContext> env=enter.getTopLevelEnv(TreeMaker.instance(inliner.getContext()).TopLevel(List.<JCTree>nil()));
  try {
    Field field=AttrContext.class.getDeclaredField("pendingResolutionPhase");
    field.setAccessible(true);
    field.set(env.info,newMethodResolutionPhase(autoboxing()));
  }
 catch (  ReflectiveOperationException e) {
    throw new LinkageError(e.getMessage(),e);
  }
  Object resultInfo;
  try {
    Class<?> resultInfoClass=Class.forName("com.sun.tools.javac.comp.Attr$ResultInfo");
    Constructor<?> resultInfoCtor=resultInfoClass.getDeclaredConstructor(Attr.class,KindSelector.class,Type.class);
    resultInfoCtor.setAccessible(true);
    resultInfo=resultInfoCtor.newInstance(Attr.instance(inliner.getContext()),KindSelector.PCK,Type.noType);
  }
 catch (  ReflectiveOperationException e) {
    throw new LinkageError(e.getMessage(),e);
  }
  Log.DeferredDiagnosticHandler handler=new Log.DeferredDiagnosticHandler(Log.instance(inliner.getContext()));
  try {
    MethodType result=callCheckMethod(warner,inliner,resultInfo,actualArgTypes,methodSymbol,site,env);
    if (!handler.getDiagnostics().isEmpty()) {
      throw new InferException(handler.getDiagnostics());
    }
    return result;
  }
  finally {
    Log.instance(inliner.getContext()).popDiagnosticHandler(handler);
  }
}
