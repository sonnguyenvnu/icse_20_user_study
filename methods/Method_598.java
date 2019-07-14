public final void skipString(){
  if (ch == '"') {
    for (int i=bp + 1; i < text.length(); ++i) {
      char c=text.charAt(i);
      if (c == '\\') {
        if (i < len - 1) {
          ++i;
          continue;
        }
      }
 else       if (c == '"') {
        this.ch=text.charAt(bp=i + 1);
        return;
      }
    }
    throw new JSONException("unclosed str");
  }
 else {
    throw new UnsupportedOperationException();
  }
}
