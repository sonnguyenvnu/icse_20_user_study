private void callParentByKeyRef(){
  context.constructorByKeyRef.addStatement("super(keyReference, value, valueReferenceQueue, weight, now)");
}
