public static SInterfaceConceptAdapterById deserialize(String s){
  String marker=INTERFACE_PREFIX + ID_DELIM;
  if (!s.startsWith(marker)) {
    throw new FormatException("Serialized form should have prefix " + marker + ":" + s);
  }
  String data=s.substring(marker.length());
  String[] split=data.split(ID_DELIM);
  if (split.length != 2) {
    throw new FormatException("Serialized form should have 2 components: " + data);
  }
  SInterfaceConcept res=MetaAdapterFactory.getInterfaceConcept(SConceptId.deserialize(split[0]),split[1]);
  if (!(res instanceof SInterfaceConceptAdapterById)) {
    throw new FormatException("Type differs from requested: " + res.getClass().getName());
  }
  return (SInterfaceConceptAdapterById)res;
}
