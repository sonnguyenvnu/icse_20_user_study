/** 
 * Reads a sequence of key/value pairs and the trailing closing brace '}' of an object. The opening brace '{' should have already been read.
 */
private JSONObject readObject() throws JSONException {
  JSONObject result=new JSONObject();
  int first=nextCleanInternal();
  if (first == '}') {
    return result;
  }
 else   if (first != -1) {
    pos--;
  }
  while (true) {
    Object name=nextValue();
    if (!(name instanceof String)) {
      if (name == null) {
        throw syntaxError("Names cannot be null");
      }
 else {
        throw syntaxError("Names must be strings, but " + name + " is of type " + name.getClass().getName());
      }
    }
    int separator=nextCleanInternal();
    if (separator != ':' && separator != '=') {
      throw syntaxError("Expected ':' after " + name);
    }
    if (pos < in.length() && in.charAt(pos) == '>') {
      pos++;
    }
    result.put((String)name,nextValue());
switch (nextCleanInternal()) {
case '}':
      return result;
case ';':
case ',':
    continue;
default :
  throw syntaxError("Unterminated object");
}
}
}
