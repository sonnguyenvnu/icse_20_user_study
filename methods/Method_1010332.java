public static InvalidReferenceLink deserialize(String s){
  String marker=INVALID_PREFIX + ID_DELIM;
  if (!s.startsWith(marker)) {
    throw new FormatException("Invalid reference should have prefix " + marker + ":" + s);
  }
  String data=s.substring(marker.length());
  String[] split=data.split("\\.");
  assert split.length == 2 : s;
  return new InvalidReferenceLink(split[0],split[1]);
}
