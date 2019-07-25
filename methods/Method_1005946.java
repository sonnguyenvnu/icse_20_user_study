public static PortBinding parse(String serialized) throws IllegalArgumentException {
  try {
    String[] parts=StringUtils.splitByWholeSeparator(serialized,":");
switch (parts.length) {
case 3:
      return createFromSubstrings(parts[0] + ":" + parts[1],parts[2]);
case 2:
    return createFromSubstrings(parts[0],parts[1]);
case 1:
  return createFromSubstrings("",parts[0]);
default :
throw new IllegalArgumentException();
}
}
 catch (Exception e) {
throw new IllegalArgumentException("Error parsing PortBinding '" + serialized + "'",e);
}
}
