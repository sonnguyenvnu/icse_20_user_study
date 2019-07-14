public static JavaTypeDefinition boxPrimitive(JavaTypeDefinition def){
  return JavaTypeDefinition.forClass(PRIMITIVE_BOXING_RULES.get(def.getType()));
}
