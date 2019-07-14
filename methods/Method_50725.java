private char checksum(String part){
  final StringBuilder sb=new StringBuilder(5);
  for (int i=4; i >= 0; i--) {
    sb.append(Character.isUpperCase(part.charAt(i)) ? '1' : '0');
  }
  return CHECKSUM_LOOKUP.get(sb.toString());
}
