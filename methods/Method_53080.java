/** 
 * Reads a sequence of values and the trailing closing brace ']' of an array. The opening brace '[' should have already been read. Note that "[]" yields an empty array, but "[,]" returns a two-element array equivalent to "[null,null]".
 */
private JSONArray readArray() throws JSONException {
  JSONArray result=new JSONArray();
  boolean hasTrailingSeparator=false;
  while (true) {
switch (nextCleanInternal()) {
case -1:
      throw syntaxError("Unterminated array");
case ']':
    if (hasTrailingSeparator) {
      result.put(null);
    }
  return result;
case ',':
case ';':
result.put(null);
hasTrailingSeparator=true;
continue;
default :
pos--;
}
result.put(nextValue());
switch (nextCleanInternal()) {
case ']':
return result;
case ',':
case ';':
hasTrailingSeparator=true;
continue;
default :
throw syntaxError("Unterminated array");
}
}
}
