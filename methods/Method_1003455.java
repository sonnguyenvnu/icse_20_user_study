private void init(){
  if (field == null) {
    Type t=base.getType();
    if (t.arrayLevel > 0) {
      if ("length".equals(name)) {
        field=new FieldObj();
        field.type=context.getClassObj("int").baseType;
      }
 else {
        throw new IllegalArgumentException("Unknown array method: " + name);
      }
    }
 else {
      field=t.classObj.getField(name);
    }
  }
}
