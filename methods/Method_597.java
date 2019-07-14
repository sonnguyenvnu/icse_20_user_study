public final void skipObject(boolean valid){
  boolean quote=false;
  int braceCnt=0;
  int i=bp;
  for (; i < text.length(); ++i) {
    final char ch=text.charAt(i);
    if (ch == '\\') {
      if (i < len - 1) {
        ++i;
        continue;
      }
 else {
        this.ch=ch;
        this.bp=i;
        throw new JSONException("illegal str, " + info());
      }
    }
 else     if (ch == '"') {
      quote=!quote;
    }
 else     if (ch == '{') {
      if (quote) {
        continue;
      }
      braceCnt++;
    }
 else     if (ch == '}') {
      if (quote) {
        continue;
      }
 else {
        braceCnt--;
      }
      if (braceCnt == -1) {
        this.bp=i + 1;
        if (this.bp == text.length()) {
          this.ch=EOI;
          this.token=JSONToken.EOF;
          return;
        }
        this.ch=text.charAt(this.bp);
        if (this.ch == ',') {
          token=JSONToken.COMMA;
          int index=++bp;
          this.ch=(index >= text.length() ? EOI : text.charAt(index));
          return;
        }
 else         if (this.ch == '}') {
          token=JSONToken.RBRACE;
          next();
          return;
        }
 else         if (this.ch == ']') {
          token=JSONToken.RBRACKET;
          next();
          return;
        }
 else {
          nextToken(JSONToken.COMMA);
        }
        return;
      }
    }
  }
  if (i == text.length()) {
    throw new JSONException("illegal str, " + info());
  }
}
