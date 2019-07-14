@Override public void configureSetter(@NonNull MethodSpec.Builder builder){
  if (javadoc != null) {
    final String name=builder.build().parameters.get(0).name;
    builder.addJavadoc(javadoc.replaceAll("(\n|^) ","$1").replaceAll("@return ((.|\n)*)$","@param " + name + " $1@return this instance\n"));
  }
}
