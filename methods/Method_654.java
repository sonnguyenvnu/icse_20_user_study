public void writeReference(Object object){
  SerialContext context=this.context;
  Object current=context.object;
  if (object == current) {
    out.write("{\"$ref\":\"@\"}");
    return;
  }
  SerialContext parentContext=context.parent;
  if (parentContext != null) {
    if (object == parentContext.object) {
      out.write("{\"$ref\":\"..\"}");
      return;
    }
  }
  SerialContext rootContext=context;
  for (; ; ) {
    if (rootContext.parent == null) {
      break;
    }
    rootContext=rootContext.parent;
  }
  if (object == rootContext.object) {
    out.write("{\"$ref\":\"$\"}");
  }
 else {
    out.write("{\"$ref\":\"");
    String path=references.get(object).toString();
    out.write(path);
    out.write("\"}");
  }
}
