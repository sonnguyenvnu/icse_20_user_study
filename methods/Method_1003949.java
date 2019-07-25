/** 
 * Tokenizes a  {@code CharSequence}, and returns a  {@code TokenizedCharSequence} as a result.
 * @param input text to be tokenized
 * @return {@code TokenizedCharSequence} instance
 */
public TokenizedCharSequence tokenize(CharSequence input){
  Preconditions.checkNotNull(input);
  return TokenizedCharSequence.createFrom(input,getDefaultTokenStream());
}
