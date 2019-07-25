@Override public int copy(ConstPool src,ConstPool dest,Map<String,String> map){
  String mname=src.getUtf8Info(memberName);
  String tdesc=src.getUtf8Info(typeDescriptor);
  tdesc=Descriptor.rename(tdesc,map);
  return dest.addNameAndTypeInfo(dest.addUtf8Info(mname),dest.addUtf8Info(tdesc));
}
