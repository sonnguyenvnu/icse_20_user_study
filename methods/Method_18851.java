public void addToTypeSpec(TypeSpec.Builder typeSpec){
  for (  JavadocSpec javadocSpec : javadocSpecs) {
    typeSpec.addJavadoc(javadocSpec.format,javadocSpec.args);
  }
  typeSpec.addAnnotations(annotationSpecs);
  typeSpec.addFields(fieldSpecs);
  typeSpec.addMethods(methodSpecs);
  typeSpec.addTypes(typeSpecs);
  typeSpec.addSuperinterfaces(superInterfaces);
}
