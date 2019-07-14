/** 
 * Collect all the <SPECIAL_TOKEN>s that are carried along with a token. Special tokens do not participate in parsing but can still trigger certain lexical actions. In some cases you may want to retrieve these special tokens, this is simply a way to extract them.
 * @param t the Token
 * @return StrBuilder with the special tokens.
 */
private static StrBuilder getSpecialText(final Token t){
  final StrBuilder sb=new StrBuilder();
  Token tmpToken=t.specialToken;
  while (tmpToken.specialToken != null) {
    tmpToken=tmpToken.specialToken;
  }
  while (tmpToken != null) {
    final String st=tmpToken.image;
    for (int i=0; i < st.length(); i++) {
      final char c=st.charAt(i);
      if (c == '#' || c == '$') {
        sb.append(c);
      }
      if (c == '\\') {
        boolean ok=true;
        boolean term=false;
        int j=i;
        for (ok=true; ok && j < st.length(); j++) {
          final char cc=st.charAt(j);
          if (cc == '\\') {
            continue;
          }
 else           if (cc == '$') {
            term=true;
            ok=false;
          }
 else {
            ok=false;
          }
        }
        if (term) {
          final String foo=st.substring(i,j);
          sb.append(foo);
          i=j;
        }
      }
    }
    tmpToken=tmpToken.next;
  }
  return sb;
}
