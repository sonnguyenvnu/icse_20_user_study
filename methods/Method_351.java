public void accept(final TypeCollector classVisitor){
  char[] c=new char[maxStringLength];
  int i, j;
  int u, v;
  int anns=0;
  if (readAnnotations) {
    u=getAttributes();
    for (i=readUnsignedShort(u); i > 0; --i) {
      String attrName=readUTF8(u + 2,c);
      if ("RuntimeVisibleAnnotations".equals(attrName)) {
        anns=u + 8;
        break;
      }
      u+=6 + readInt(u + 4);
    }
  }
  u=header;
  v=items[readUnsignedShort(u + 4)];
  int len=readUnsignedShort(u + 6);
  u+=8;
  for (i=0; i < len; ++i) {
    u+=2;
  }
  v=u;
  i=readUnsignedShort(v);
  v+=2;
  for (; i > 0; --i) {
    j=readUnsignedShort(v + 6);
    v+=8;
    for (; j > 0; --j) {
      v+=6 + readInt(v + 2);
    }
  }
  i=readUnsignedShort(v);
  v+=2;
  for (; i > 0; --i) {
    j=readUnsignedShort(v + 6);
    v+=8;
    for (; j > 0; --j) {
      v+=6 + readInt(v + 2);
    }
  }
  i=readUnsignedShort(v);
  v+=2;
  for (; i > 0; --i) {
    v+=6 + readInt(v + 2);
  }
  if (anns != 0) {
    for (i=readUnsignedShort(anns), v=anns + 2; i > 0; --i) {
      String name=readUTF8(v,c);
      classVisitor.visitAnnotation(name);
    }
  }
  i=readUnsignedShort(u);
  u+=2;
  for (; i > 0; --i) {
    j=readUnsignedShort(u + 6);
    u+=8;
    for (; j > 0; --j) {
      u+=6 + readInt(u + 2);
    }
  }
  i=readUnsignedShort(u);
  u+=2;
  for (; i > 0; --i) {
    u=readMethod(classVisitor,c,u);
  }
}
