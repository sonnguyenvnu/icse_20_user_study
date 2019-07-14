public int seekObjectToField(long fieldNameHash,boolean deepScan){
  if (token == JSONToken.EOF) {
    return JSONLexer.NOT_MATCH;
  }
  if (token == JSONToken.RBRACE || token == JSONToken.RBRACKET) {
    nextToken();
    return JSONLexer.NOT_MATCH;
  }
  if (token != JSONToken.LBRACE && token != JSONToken.COMMA) {
    throw new UnsupportedOperationException(JSONToken.name(token));
  }
  for (; ; ) {
    if (ch == '}') {
      next();
      nextToken();
      return JSONLexer.NOT_MATCH;
    }
    if (ch == EOI) {
      return JSONLexer.NOT_MATCH;
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
    if (hash == fieldNameHash) {
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
      return VALUE;
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
 else     if (ch == 't') {
      next();
      if (ch == 'r') {
        next();
        if (ch == 'u') {
          next();
          if (ch == 'e') {
            next();
          }
        }
      }
      if (ch != ',' && ch != '}') {
        skipWhitespace();
      }
      if (ch == ',') {
        next();
      }
    }
 else     if (ch == 'n') {
      next();
      if (ch == 'u') {
        next();
        if (ch == 'l') {
          next();
          if (ch == 'l') {
            next();
          }
        }
      }
      if (ch != ',' && ch != '}') {
        skipWhitespace();
      }
      if (ch == ',') {
        next();
      }
    }
 else     if (ch == 'f') {
      next();
      if (ch == 'a') {
        next();
        if (ch == 'l') {
          next();
          if (ch == 's') {
            next();
            if (ch == 'e') {
              next();
            }
          }
        }
      }
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
      if (deepScan) {
        token=JSONToken.LBRACE;
        return OBJECT;
      }
      skipObject(false);
      if (token == JSONToken.RBRACE) {
        return JSONLexer.NOT_MATCH;
      }
    }
 else     if (ch == '[') {
      next();
      if (deepScan) {
        token=JSONToken.LBRACKET;
        return ARRAY;
      }
      skipArray(false);
      if (token == JSONToken.RBRACE) {
        return JSONLexer.NOT_MATCH;
      }
    }
 else {
      throw new UnsupportedOperationException();
    }
  }
}
