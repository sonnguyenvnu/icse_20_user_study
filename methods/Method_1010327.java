public static InvalidProperty deserialize(String s){
  String marker=INVALID_PREFIX + ID_DELIM;
  if (!s.startsWith(marker)) {
    throw new FormatException("Invalid property should have prefix " + marker + ":" + s);
  }
  String data=s.substring(marker.length());
  String[] split=data.split("\\.");
  assert split.length == 2 : s;
  return new InvalidProperty(split[0],split[1]);
}
