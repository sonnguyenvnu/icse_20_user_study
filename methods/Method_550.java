public final void scanHex(){
  if (ch != 'x') {
    throw new JSONException("illegal state. " + ch);
  }
  next();
  if (ch != '\'') {
    throw new JSONException("illegal state. " + ch);
  }
  np=bp;
  next();
  if (ch == '\'') {
    next();
    token=JSONToken.HEX;
    return;
  }
  for (int i=0; ; ++i) {
    char ch=next();
    if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F')) {
      sp++;
      continue;
    }
 else     if (ch == '\'') {
      sp++;
      next();
      break;
    }
 else {
      throw new JSONException("illegal state. " + ch);
    }
  }
  token=JSONToken.HEX;
}
