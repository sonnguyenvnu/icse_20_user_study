private void callParentByKey(){
  context.constructorByKey.addStatement("super(key, keyReferenceQueue, value, valueReferenceQueue, weight, now)");
}
