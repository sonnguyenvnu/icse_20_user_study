/** 
 * Setup highlighting for suggestions.  If this is called a highlight field is returned with suggestions wrapping changed tokens with preTag and postTag.
 */
public PhraseSuggestionBuilder highlight(String preTag,String postTag){
  if ((preTag == null) != (postTag == null)) {
    throw new IllegalArgumentException("Pre and post tag must both be null or both not be null.");
  }
  this.preTag=preTag;
  this.postTag=postTag;
  return this;
}
