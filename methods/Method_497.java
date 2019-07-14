public final void nextTokenWithChar(char expect){
  sp=0;
  for (; ; ) {
    if (ch == expect) {
      next();
      nextToken();
      return;
    }
    if (ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t' || ch == '\f' || ch == '\b') {
      next();
      continue;
    }
    throw new JSONException("not match " + expect + " - " + ch + ", info : " + this.info());
  }
}
