public void addInstantiatable(@NonNull String name){
  methodBuilder.addStatement("$T.check($L)",ClassValidator.class,name);
}
