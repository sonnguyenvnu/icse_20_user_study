/** 
 * Parse a string as a  {@link WebSocketExtension}. The input string should comply with the format described in <a href= "https://tools.ietf.org/html/rfc6455#section-9.1">9.1. Negotiating Extensions</a> in <a href="https://tools.ietf.org/html/rfc6455" >RFC 6455</a>.
 * @param string A string that represents a WebSocket extension.
 * @return A new  {@link WebSocketExtension} instance that representsthe given string. If the input string does not comply with RFC 6455,  {@code null} is returned.
 */
public static WebSocketExtension parse(String string){
  if (string == null) {
    return null;
  }
  String[] elements=string.trim().split("\\s*;\\s*");
  if (elements.length == 0) {
    return null;
  }
  String name=elements[0];
  if (Token.isValid(name) == false) {
    return null;
  }
  WebSocketExtension extension=createInstance(name);
  for (int i=1; i < elements.length; ++i) {
    String[] pair=elements[i].split("\\s*=\\s*",2);
    if (pair.length == 0 || pair[0].length() == 0) {
      continue;
    }
    String key=pair[0];
    if (Token.isValid(key) == false) {
      continue;
    }
    String value=extractValue(pair);
    if (value != null) {
      if (Token.isValid(value) == false) {
        continue;
      }
    }
    extension.setParameter(key,value);
  }
  return extension;
}
