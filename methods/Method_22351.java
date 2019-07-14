public void addNotEmpty(@NonNull String name){
  methodBuilder.beginControlFlow("if ($L.length == 0)",name).addStatement("throw new $T(\"$L cannot be empty\")",ACRAConfigurationException.class,name).endControlFlow();
}
