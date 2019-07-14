private static void writeSymJson(Binding binding,JsonGenerator json) throws IOException {
  if (binding.start < 0) {
    return;
  }
  String name=binding.name;
  boolean isExported=!(Binding.Kind.VARIABLE == binding.kind || Binding.Kind.PARAMETER == binding.kind || Binding.Kind.SCOPE == binding.kind || Binding.Kind.ATTRIBUTE == binding.kind || (name.length() == 0 || name.charAt(0) == '_' || name.startsWith("lambda%")));
  String path=binding.qname.replace('.','/').replace("%20",".");
  if (!seenDef.contains(path)) {
    seenDef.add(path);
    json.writeStartObject();
    json.writeStringField("name",name);
    json.writeStringField("path",path);
    json.writeStringField("file",binding.fileOrUrl);
    json.writeNumberField("identStart",binding.start);
    json.writeNumberField("identEnd",binding.end);
    json.writeNumberField("defStart",binding.bodyStart);
    json.writeNumberField("defEnd",binding.bodyEnd);
    json.writeBooleanField("exported",isExported);
    json.writeStringField("kind",binding.kind.toString());
    if (Binding.Kind.FUNCTION == binding.kind || Binding.Kind.METHOD == binding.kind || Binding.Kind.CONSTRUCTOR == binding.kind) {
      json.writeObjectFieldStart("funcData");
      String argExpr=null;
      Type t=binding.type;
      if (t instanceof UnionType) {
        t=((UnionType)t).firstUseful();
      }
      if (t != null && t instanceof FunType) {
        FunctionDef func=((FunType)t).func;
        if (func != null) {
          argExpr=func.getArgumentExpr();
        }
      }
      String typeExpr=binding.type.toString();
      json.writeNullField("params");
      String signature=argExpr == null ? "" : argExpr + "\n" + typeExpr;
      json.writeStringField("signature",signature);
      json.writeEndObject();
    }
    json.writeEndObject();
  }
}
