public final void skipWhitespace(){
  for (; ; ) {
    if (ch <= '/') {
      if (ch == ' ' || ch == '\r' || ch == '\n' || ch == '\t' || ch == '\f' || ch == '\b') {
        next();
        continue;
      }
 else       if (ch == '/') {
        skipComment();
        continue;
      }
 else {
        break;
      }
    }
 else {
      break;
    }
  }
}
