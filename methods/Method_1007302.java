private void read(MethodInfo src,String methodname,Map<String,String> classnames){
  ConstPool destCp=constPool;
  accessFlags=src.accessFlags;
  name=destCp.addUtf8Info(methodname);
  cachedName=methodname;
  ConstPool srcCp=src.constPool;
  String desc=srcCp.getUtf8Info(src.descriptor);
  String desc2=Descriptor.rename(desc,classnames);
  descriptor=destCp.addUtf8Info(desc2);
  attribute=new ArrayList<AttributeInfo>();
  ExceptionsAttribute eattr=src.getExceptionsAttribute();
  if (eattr != null)   attribute.add(eattr.copy(destCp,classnames));
  CodeAttribute cattr=src.getCodeAttribute();
  if (cattr != null)   attribute.add(cattr.copy(destCp,classnames));
}
