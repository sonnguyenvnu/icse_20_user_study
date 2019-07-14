CodeBlock render(boolean debuggable){
  CodeBlock.Builder builder=CodeBlock.builder().add("target.$L = $T.$L(",name,UTILS,kind.factoryName);
  for (int i=0; i < ids.size(); i++) {
    if (i > 0) {
      builder.add(", ");
    }
    builder.add("\n");
    Id id=ids.get(i);
    boolean requiresCast=requiresCast(type);
    if (!debuggable) {
      if (requiresCast) {
        builder.add("($T) ",type);
      }
      builder.add("source.findViewById($L)",id.code);
    }
 else     if (!requiresCast && !required) {
      builder.add("source.findViewById($L)",id.code);
    }
 else {
      builder.add("$T.find",UTILS);
      builder.add(required ? "RequiredView" : "OptionalView");
      if (requiresCast) {
        builder.add("AsType");
      }
      builder.add("(source, $L, \"field '$L'\"",id.code,name);
      if (requiresCast) {
        TypeName rawType=type;
        if (rawType instanceof ParameterizedTypeName) {
          rawType=((ParameterizedTypeName)rawType).rawType;
        }
        builder.add(", $T.class",rawType);
      }
      builder.add(")");
    }
  }
  return builder.add(")").build();
}
