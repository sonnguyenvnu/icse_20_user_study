/** 
 * This resets this tokenizer with a new string and/or delimiter.
 * @param string containing tokens
 * @param delim single character to split the tokens
 * @return number of tokens
 */
public int tokenize(String string,char delim){
  tokens=new ArrayList<String>();
  int nest=0;
  int p;
  int s;
  boolean skipChar=false;
  boolean nestedDoubleQuote=false;
  for (p=0, s=0; p < string.length(); p++) {
    char c=string.charAt(p);
    if (c == '(' || c == '[' || c == '<' || (!nestedDoubleQuote && !skipChar && c == '"')) {
      nest++;
      if (c == '"') {
        nestedDoubleQuote=true;
        skipChar=true;
      }
    }
    if (c == ')' || c == ']' || c == '>' || (nestedDoubleQuote && !skipChar && c == '"')) {
      nest--;
      if (c == '"') {
        nestedDoubleQuote=false;
      }
    }
    skipChar=c == '\\';
    if (nest == 0 && c == delim) {
      tokens.add(string.substring(s,p));
      s=p + 1;
    }
  }
  if (s < string.length()) {
    tokens.add(string.substring(s));
  }
  return tokens.size();
}
