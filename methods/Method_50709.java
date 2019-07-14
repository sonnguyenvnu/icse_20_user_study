private static List<PropertyDescriptor<List<String>>> suffixOrPrefixProperties(){
  List<PropertyDescriptor<List<String>>> res=new ArrayList<>();
  res.add(STATIC_PREFIXES_DESCRIPTOR);
  res.add(STATIC_SUFFIXES_DESCRIPTOR);
  res.add(MEMBER_PREFIXES_DESCRIPTOR);
  res.add(MEMBER_SUFFIXES_DESCRIPTOR);
  res.add(LOCAL_PREFIXES_DESCRIPTOR);
  res.add(LOCAL_SUFFIXES_DESCRIPTOR);
  res.add(PARAMETER_PREFIXES_DESCRIPTOR);
  res.add(PARAMETER_SUFFIXES_DESCRIPTOR);
  return res;
}
