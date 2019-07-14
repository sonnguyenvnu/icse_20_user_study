public void addNotUnset(@NonNull String name,@NonNull TypeName type){
  methodBuilder.beginControlFlow("if ($L == $L)",name,getDefault(type)).addStatement("throw new $T(\"$L has to be set\")",ACRAConfigurationException.class,name).endControlFlow();
}
