/** 
 * Splits a codecs sequence string, as defined in RFC 6381, into individual codec strings.
 * @param codecs A codec sequence string, as defined in RFC 6381.
 * @return The split codecs, or an array of length zero if the input was empty.
 */
public static String[] splitCodecs(String codecs){
  if (TextUtils.isEmpty(codecs)) {
    return new String[0];
  }
  return split(codecs.trim(),"(\\s*,\\s*)");
}
