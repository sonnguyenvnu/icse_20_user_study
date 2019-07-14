public int seekObjectToField(long[] fieldNameHash){
  if (token != JSONToken.LBRACE && token != JSONToken.COMMA) {
    throw new UnsupportedOperationException();
  }
  for (; ; ) {
    if (ch == '}') {
      next();
      nextToken();
      this.matchStat=JSONLexer.NOT_MATCH;
      return -1;
    }
    if (ch == EOI) {
      this.matchStat=JSONLexer.NOT_MATCH;
      return -1;
    }
    if (ch != '"') {
      skipWhitespace();
    }
    long hash;
    if (ch == '"') {
      hash=0xcbf29ce484222325L;
      for (int i=bp + 1; i < text.length(); ++i) {
        char c=text.charAt(i);
        if (c == '\\') {
          ++i;
          if (i == text.length()) {
            throw new JSONException("unclosed str, " + info());
          }
          c=text.charAt(i);
        }
        if (c == '"') {
          bp=i + 1;
          ch=(bp >= text.length() ? EOI : text.charAt(bp));
          break;
        }
        hash^=c;
        hash*=0x100000001b3L;
      }
    }
 else {
      throw new UnsupportedOperationException();
    }
    int matchIndex=-1;
    for (int i=0; i < fieldNameHash.length; i++) {
      if (hash == fieldNameHash[i]) {
        matchIndex=i;
        break;
      }
    }
    if (matchIndex != -1) {
      if (ch != ':') {
        skipWhitespace();
      }
      if (ch == ':') {
{
          int index=++bp;
          ch=(index >= text.length() ? EOI : text.charAt(index));
        }
        if (ch == ',') {
{
            int index=++bp;
            ch=(index >= text.length() ? EOI : text.charAt(index));
          }
          token=JSONToken.COMMA;
        }
 else         if (ch == ']') {
{
            int index=++bp;
            ch=(index >= text.length() ? EOI : text.charAt(index));
          }
          token=JSONToken.RBRACKET;
        }
 else         if (ch == '}') {
{
            int index=++bp;
            ch=(index >= text.length() ? EOI : text.charAt(index));
          }
          token=JSONToken.RBRACE;
        }
 else         if (ch >= '0' && ch <= '9') {
          sp=0;
          pos=bp;
          scanNumber();
        }
 else {
          nextToken(JSONToken.LITERAL_INT);
        }
      }
      matchStat=VALUE;
      return matchIndex;
    }
    if (ch != ':') {
      skipWhitespace();
    }
    if (ch == ':') {
      int index=++bp;
      ch=(index >= text.length() ? EOI : text.charAt(index));
    }
 else {
      throw new JSONException("illegal json, " + info());
    }
    if (ch != '"' && ch != '\'' && ch != '{' && ch != '[' && ch != '0' && ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != '7' && ch != '8' && ch != '9' && ch != '+' && ch != '-') {
      skipWhitespace();
    }
    if (ch == '-' || ch == '+' || (ch >= '0' && ch <= '9')) {
      next();
      while (ch >= '0' && ch <= '9') {
        next();
      }
      if (ch == '.') {
        next();
        while (ch >= '0' && ch <= '9') {
          next();
        }
      }
      if (ch == 'E' || ch == 'e') {
        next();
        if (ch == '-' || ch == '+') {
          next();
        }
        while (ch >= '0' && ch <= '9') {
          next();
        }
      }
      if (ch != ',') {
        skipWhitespace();
      }
      if (ch == ',') {
        next();
      }
    }
 else     if (ch == '"') {
      skipString();
      if (ch != ',' && ch != '}') {
        skipWhitespace();
      }
      if (ch == ',') {
        next();
      }
    }
 else     if (ch == '{') {
{
        int index=++bp;
        ch=(index >= text.length() ? EOI : text.charAt(index));
      }
      skipObject(false);
    }
 else     if (ch == '[') {
      next();
      skipArray(false);
    }
 else {
      throw new UnsupportedOperationException();
    }
  }
}
