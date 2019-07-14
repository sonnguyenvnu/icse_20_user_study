private void assignKeyRefAndValue(){
  context.constructorByKeyRef.addStatement("$T.UNSAFE.putObject(this, $N, $N)",UNSAFE_ACCESS,offsetName("key"),"keyReference");
  if (isStrongValues()) {
    context.constructorByKeyRef.addStatement("$T.UNSAFE.putObject(this, $N, $N)",UNSAFE_ACCESS,offsetName("value"),"value");
  }
 else {
    context.constructorByKeyRef.addStatement("$T.UNSAFE.putObject(this, $N, new $T($N, $N, $N))",UNSAFE_ACCESS,offsetName("value"),valueReferenceType(),"keyReference","value","valueReferenceQueue");
  }
}
