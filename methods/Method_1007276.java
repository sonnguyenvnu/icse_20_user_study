/** 
 * Ends writing and returns the contents of the class file.
 * @param accessFlags       access flags.
 * @param thisClass         this class.  an index indicating its <code>CONSTANT_Class_info</code>.
 * @param superClass        super class.  an index indicating its <code>CONSTANT_Class_info</code>.
 * @param interfaces        implemented interfaces.index numbers indicating their <code>ClassInfo</code>. It may be null.
 * @param aw        attributes of the class file.  May be null.
 * @see AccessFlag
 */
public byte[] end(int accessFlags,int thisClass,int superClass,int[] interfaces,AttributeWriter aw){
  constPool.end();
  output.writeShort(accessFlags);
  output.writeShort(thisClass);
  output.writeShort(superClass);
  if (interfaces == null)   output.writeShort(0);
 else {
    int n=interfaces.length;
    output.writeShort(n);
    for (int i=0; i < n; i++)     output.writeShort(interfaces[i]);
  }
  output.enlarge(fields.dataSize() + methods.dataSize() + 6);
  try {
    output.writeShort(fields.size());
    fields.write(output);
    output.writeShort(methods.numOfMethods());
    methods.write(output);
  }
 catch (  IOException e) {
  }
  writeAttribute(output,aw,0);
  return output.toByteArray();
}
