public final void scanFalse(){
  if (ch != 'f') {
    throw new JSONException("error parse false");
  }
  next();
  if (ch != 'a') {
    throw new JSONException("error parse false");
  }
  next();
  if (ch != 'l') {
    throw new JSONException("error parse false");
  }
  next();
  if (ch != 's') {
    throw new JSONException("error parse false");
  }
  next();
  if (ch != 'e') {
    throw new JSONException("error parse false");
  }
  next();
  if (ch == ' ' || ch == ',' || ch == '}' || ch == ']' || ch == '\n' || ch == '\r' || ch == '\t' || ch == EOI || ch == '\f' || ch == '\b' || ch == ':' || ch == '/') {
    token=JSONToken.FALSE;
  }
 else {
    throw new JSONException("scan false error");
  }
}
