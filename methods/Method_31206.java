/** 
 * Splits this string into list of Long
 * @param versionStr The string to split.
 * @return The resulting array.
 */
private List<BigInteger> tokenize(String versionStr){
  List<BigInteger> parts=new ArrayList<>();
  for (  String part : SPLIT_REGEX.split(versionStr)) {
    parts.add(toBigInteger(versionStr,part));
  }
  for (int i=parts.size() - 1; i > 0; i--) {
    if (!parts.get(i).equals(BigInteger.ZERO)) {
      break;
    }
    parts.remove(i);
  }
  return parts;
}
