@Override public Constructor<?> getMember(){
  if (ClassLoaderUtil.CLINIT.equals(name)) {
    return null;
  }
 else {
    Constructor<?> constructor=constructorReference == null ? null : constructorReference.get();
    if (constructor == null) {
      SignatureReader signatureReader=new SignatureReader(desc);
      TypeSignatureVisitor visitor=new TypeSignatureVisitor();
      signatureReader.accept(visitor);
      constructor=ClassLoaderUtil.getConstructor(super.getClassNode().getType(),name,visitor.getMethodParameterTypes());
      constructorReference=new WeakReference<Constructor<?>>(constructor);
    }
    return constructor;
  }
}
