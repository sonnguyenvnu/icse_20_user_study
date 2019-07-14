protected void skipComment(){
  next();
  if (ch == '/') {
    for (; ; ) {
      next();
      if (ch == '\n') {
        next();
        return;
      }
 else       if (ch == EOI) {
        return;
      }
    }
  }
 else   if (ch == '*') {
    next();
    for (; ch != EOI; ) {
      if (ch == '*') {
        next();
        if (ch == '/') {
          next();
          return;
        }
 else {
          continue;
        }
      }
      next();
    }
  }
 else {
    throw new JSONException("invalid comment");
  }
}
