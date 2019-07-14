protected char writeClassName(SerializeWriter out,Class<?> clazz,char sep){
  if (out.isEnabled(SerializerFeature.WriteClassName)) {
    out.write('{');
    out.writeFieldName(JSON.DEFAULT_TYPE_KEY);
    out.writeString(clazz.getName());
    sep=',';
  }
  return sep;
}
