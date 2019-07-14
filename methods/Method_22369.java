default void configureGetter(@NonNull MethodSpec.Builder builder){
  builder.addStatement("return $L",getName());
}
