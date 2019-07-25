/** 
 * Ends writing and writes the contents of the class file into the given output stream.
 * @param accessFlags       access flags.
 * @param thisClass         this class.  an index indicating its <code>CONSTANT_Class_info</code>.
 * @param superClass        super class.  an index indicating its <code>CONSTANT_Class_info</code>.
 * @param interfaces        implemented interfaces.index numbers indicating their <code>CONSTATNT_Class_info</code>. It may be null.
 * @param aw        attributes of the class file.  May be null.
 * @see AccessFlag
 */
public void end(DataOutputStream out,int accessFlags,int thisClass,int superClass,int[] interfaces,AttributeWriter aw) throws IOException {
  constPool.end();
  output.writeTo(out);
  out.writeShort(accessFlags);
  out.writeShort(thisClass);
  out.writeShort(superClass);
  if (interfaces == null)   out.writeShort(0);
 else {
    int n=interfaces.length;
    out.writeShort(n);
    for (int i=0; i < n; i++)     out.writeShort(interfaces[i]);
  }
  out.writeShort(fields.size());
  fields.write(out);
  out.writeShort(methods.numOfMethods());
  methods.write(out);
  if (aw == null)   out.writeShort(0);
 else {
    out.writeShort(aw.size());
    aw.write(out);
  }
}
