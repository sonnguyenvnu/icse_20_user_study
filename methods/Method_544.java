public final void scanNullOrNew(boolean acceptColon){
  if (ch != 'n') {
    throw new JSONException("error parse null or new");
  }
  next();
  if (ch == 'u') {
    next();
    if (ch != 'l') {
      throw new JSONException("error parse null");
    }
    next();
    if (ch != 'l') {
      throw new JSONException("error parse null");
    }
    next();
    if (ch == ' ' || ch == ',' || ch == '}' || ch == ']' || ch == '\n' || ch == '\r' || ch == '\t' || ch == EOI || (ch == ':' && acceptColon) || ch == '\f' || ch == '\b') {
      token=JSONToken.NULL;
    }
 else {
      throw new JSONException("scan null error");
    }
    return;
  }
  if (ch != 'e') {
    throw new JSONException("error parse new");
  }
  next();
  if (ch != 'w') {
    throw new JSONException("error parse new");
  }
  next();
  if (ch == ' ' || ch == ',' || ch == '}' || ch == ']' || ch == '\n' || ch == '\r' || ch == '\t' || ch == EOI || ch == '\f' || ch == '\b') {
    token=JSONToken.NEW;
  }
 else {
    throw new JSONException("scan new error");
  }
}
