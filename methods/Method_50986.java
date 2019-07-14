@Override public Method getMember(){
  Method method=methodReference == null ? null : methodReference.get();
  if (method == null) {
    SignatureReader signatureReader=new SignatureReader(desc);
    TypeSignatureVisitor visitor=new TypeSignatureVisitor();
    signatureReader.accept(visitor);
    method=ClassLoaderUtil.getMethod(super.getClassNode().getType(),name,visitor.getMethodParameterTypes());
    methodReference=new WeakReference<>(method);
  }
  return method;
}
