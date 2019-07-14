private void addConstants(){
  List<String> constants=ImmutableList.of("maximum","windowMaximum","mainProtectedMaximum","weightedSize","windowWeightedSize","mainProtectedWeightedSize");
  for (  String constant : constants) {
    String name=CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,constant);
    factory.addField(FieldSpec.builder(String.class,name).addModifiers(Modifier.PUBLIC,Modifier.STATIC,Modifier.FINAL).initializer("$S",constant).build());
  }
  constants=ImmutableList.of("key","value","accessTime","writeTime");
  for (  String constant : constants) {
    String name=CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,constant);
    factory.addField(FieldSpec.builder(String.class,name).addModifiers(Modifier.PUBLIC,Modifier.STATIC,Modifier.FINAL).initializer("$S",constant).build());
  }
}
