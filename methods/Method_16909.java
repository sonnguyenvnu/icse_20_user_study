private void callSiblingConstructor(){
  if (isStrongKeys()) {
    context.constructorByKey.addStatement("this(key, value, valueReferenceQueue, weight, now)");
  }
 else {
    context.constructorByKey.addStatement("this(new $T($N, $N), value, valueReferenceQueue, weight, now)",keyReferenceType(),"key","keyReferenceQueue");
  }
}
