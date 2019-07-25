/** 
 * <p> This method takes a String key and a map from Strings to values of any type. During processing, the method will identify the most specific key in the map that matches the line. Once the correct is identified, its value is returned. Note that if the map contains the wildcard string "*" as a key, then it will serve as the "default" case, matching every line that does not match anything else. <p> If no matching prefix is found, a  {@link IllegalStateException} will bethrown. <p> Null keys are not allowed in the map.
 * @param line An input string
 * @return the value whose prefix matches the given line
 */
public S match(String line){
  S value=null;
  Assert.notNull(line,"A non-null key must be provided to match against.");
  for (  String key : sorted) {
    if (PatternMatcher.match(key,line)) {
      value=map.get(key);
      break;
    }
  }
  if (value == null) {
    throw new IllegalStateException("Could not find a matching pattern for key=[" + line + "]");
  }
  return value;
}
