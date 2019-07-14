/** 
 * Creates method signature from method name.
 */
protected MethodSignatureVisitor createMethodSignature(final int access,final String methodName,final String description,final String signature,final String[] exceptions,final String classname,final Map<String,String> declaredTypeGenerics){
  MethodSignatureVisitor v=new MethodSignatureVisitor(methodName,access,classname,description,exceptions,signature,declaredTypeGenerics,this);
  new SignatureReader(signature != null ? signature : description).accept(v);
  return v;
}
