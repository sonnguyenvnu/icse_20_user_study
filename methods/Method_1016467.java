/** 
 * check if the parser supports the given content.
 * @param url
 * @param mimeType
 * @return returns null if the content is supported. If the content is not supported, return a error string.
 */
public static String supports(final MultiProtocolURL url,final String mimeType){
  try {
    final Set<Parser> idioms=parsers(url,mimeType);
    return (idioms == null || idioms.isEmpty() || (idioms.size() == 1 && idioms.iterator().next().getName().equals(genericIdiom.getName()))) ? "no parser found" : null;
  }
 catch (  final Parser.Failure e) {
    return e.getMessage();
  }
}
