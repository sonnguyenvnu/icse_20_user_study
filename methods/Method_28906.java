/** 
 * Retrieving external keys from the result of the search. <p> Takes a pattern that is used in order to generate the key names of the result of sorting. The key names are obtained substituting the first occurrence of * with the actual value of the elements on the list. <p> The pattern for a normal key/value pair is "keyname*" and for a value in a hash "keyname*-&gt;fieldname". <p> To get the list itself use the char # as pattern.
 * @param patterns
 * @return the SortingParams Object
 */
public SortingParams get(byte[]... patterns){
  for (  final byte[] pattern : patterns) {
    params.add(GET.raw);
    params.add(pattern);
  }
  return this;
}
