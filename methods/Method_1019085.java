private static void annotation(TextHBox t,AnnotationNode item){
  if (item.desc != null) {
    addRaw(t,"@");
    addType(t,Type.getType(item.desc));
  }
  if (item instanceof TypeAnnotationNode) {
    TypeAnnotationNode itemType=(TypeAnnotationNode)item;
    addRaw(t," Path:");
    addRaw(t,itemType.typePath.toString());
    addRaw(t," Ref:");
    addRaw(t,RefType.fromSort(itemType.typeRef).name());
    addRaw(t," ");
  }
  int max=item.values == null ? 0 : item.values.size();
  if (max == 0) {
    return;
  }
  addRaw(t,"(");
  for (int i=0; i < max; i+=2) {
    String name=(String)item.values.get(i);
    Object value=item.values.get(i + 1);
    if (max > 2 || !"value".equals(name)) {
      addName(t,name);
      addRaw(t,"=");
    }
    if (value instanceof String) {
      addString(t,"\"" + value.toString() + "\"");
    }
 else     if (value instanceof Type) {
      Type type=(Type)value;
      if (TypeUtil.isInternal(type)) {
        addName(t,type.getInternalName());
      }
 else       if (TypeUtil.isStandard(type.toString())) {
        addType(t,type);
      }
 else {
        Logging.warn("Unknown annotation type format in value: @" + (i + 1) + " type: " + value.toString());
        addRaw(t,type.getDescriptor());
      }
    }
 else     if (value instanceof Number) {
      addValue(t,value.toString());
    }
 else     if (value instanceof List) {
      List<?> l=(List<?>)value;
      if (l.isEmpty()) {
        addRaw(t,"[]");
      }
 else {
        addRaw(t,"[");
        Object first=l.get(0);
        Type type;
        if (first instanceof String[]) {
          type=Type.getType(Enum.class);
        }
 else         if (first instanceof Type) {
          type=Type.getType(Class.class);
        }
 else         if (first instanceof AnnotationNode) {
          type=Type.getType(Annotation.class);
        }
 else {
          type=Type.getType(first.getClass());
        }
        addType(t,type);
        addRaw(t,"...]");
      }
    }
 else     if (value instanceof String[]) {
      String[] str=(String[])value;
      Type enumType=Type.getType(str[0]);
      addType(t,enumType);
      addRaw(t,".");
      addName(t,str[1]);
    }
 else     if (value instanceof AnnotationNode) {
      annotation(t,(AnnotationNode)value);
    }
 else {
      Logging.fine("Unknown annotation data-type: @" + i + " type: " + value.getClass());
    }
    if (i + 2 < max) {
      addRaw(t,", ");
    }
  }
  addRaw(t,")");
}
