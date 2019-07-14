@Override public void configureGetter(@NonNull MethodSpec.Builder builder){
  builder.addStatement("return $L.$L($L)",Strings.FIELD_DELEGATE,name,getName());
}
