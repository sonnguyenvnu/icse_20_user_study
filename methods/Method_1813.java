/** 
 * Creates an instance of BytesRange by parsing the value of a returned HTTP "Content-Range" header. <p> If the range runs to the end of the available content, the end of the range will be set to TO_END_OF_CONTENT. <p> The header spec is at https://tools.ietf.org/html/rfc2616#section-14.16
 * @param header
 * @throws IllegalArgumentException if the header is non-null but fails to match the format perthe spec
 * @return the parsed range
 */
@Nullable public static BytesRange fromContentRangeHeader(@Nullable String header) throws IllegalArgumentException {
  if (header == null) {
    return null;
  }
  if (sHeaderParsingRegEx == null) {
    sHeaderParsingRegEx=Pattern.compile("[-/ ]");
  }
  try {
    final String[] headerParts=sHeaderParsingRegEx.split(header);
    Preconditions.checkArgument(headerParts.length == 4);
    Preconditions.checkArgument(headerParts[0].equals("bytes"));
    final int from=Integer.parseInt(headerParts[1]);
    final int to=Integer.parseInt(headerParts[2]);
    final int length=Integer.parseInt(headerParts[3]);
    Preconditions.checkArgument(to > from);
    Preconditions.checkArgument(length > to);
    if (to < length - 1) {
      return new BytesRange(from,to);
    }
 else {
      return new BytesRange(from,TO_END_OF_CONTENT);
    }
  }
 catch (  IllegalArgumentException x) {
    throw new IllegalArgumentException(String.format((Locale)null,"Invalid Content-Range header value: \"%s\"",header),x);
  }
}
