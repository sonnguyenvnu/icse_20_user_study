/** 
 * Returns the bytecode of the class that was build with this class writer.
 * @return the bytecode of the class that was build with this class writer.
 */
public byte[] toByteArray(){
  int size=24 + 2 * interfaceCount;
  int nbFields=0;
  FieldWriter fb=firstField;
  while (fb != null) {
    ++nbFields;
    size+=fb.getSize();
    fb=fb.next;
  }
  int nbMethods=0;
  MethodWriter mb=firstMethod;
  while (mb != null) {
    ++nbMethods;
    size+=mb.getSize();
    mb=mb.next;
  }
  int attributeCount=0;
  size+=pool.length;
  ByteVector out=new ByteVector(size);
  out.putInt(0xCAFEBABE).putInt(version);
  out.putShort(index).putByteArray(pool.data,0,pool.length);
  int mask=393216;
  out.putShort(access & ~mask).putShort(name).putShort(superName);
  out.putShort(interfaceCount);
  for (int i=0; i < interfaceCount; ++i) {
    out.putShort(interfaces[i]);
  }
  out.putShort(nbFields);
  fb=firstField;
  while (fb != null) {
    fb.put(out);
    fb=fb.next;
  }
  out.putShort(nbMethods);
  mb=firstMethod;
  while (mb != null) {
    mb.put(out);
    mb=mb.next;
  }
  out.putShort(attributeCount);
  return out.data;
}
