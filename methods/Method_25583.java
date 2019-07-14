/** 
 * If the given regexes matches exactly one string, returns that string. Otherwise returns  {@code null}. This can be used to identify arguments to e.g.  {@code String.replaceAll} that don't needto be regexes.
 */
public static Optional<String> convertRegexToLiteral(String s){
  try {
    Pattern.compile(s);
  }
 catch (  PatternSyntaxException e) {
    return Optional.empty();
  }
  boolean inQuote=false;
  StringBuilder result=new StringBuilder();
  int length=s.length();
  for (int i=0; i < length; ++i) {
    char current=s.charAt(i);
    if (!inQuote && UNESCAPED_CONSTRUCT.matches(current)) {
      return Optional.empty();
    }
 else     if (current == '\\') {
      char escaped=s.charAt(++i);
      if (escaped == 'Q') {
        inQuote=true;
      }
 else       if (escaped == 'E') {
        inQuote=false;
      }
 else {
        Character controlChar=REGEXCHAR_TO_LITERALCHAR.get(escaped);
        if (controlChar != null) {
          result.append(controlChar);
        }
 else         if (escaped == '\\') {
          result.append('\\');
        }
 else         if (UNESCAPED_CONSTRUCT.matches(escaped)) {
          result.append(escaped);
        }
 else {
          return Optional.empty();
        }
      }
    }
 else {
      result.append(current);
    }
  }
  return Optional.of(result.toString());
}
