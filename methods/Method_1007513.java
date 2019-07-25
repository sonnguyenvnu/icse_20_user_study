public T remove(final int idx){
  assert idx >= 0 : "negative index";
  T msg;
synchronized (this) {
    int n=numMsgs;
    assert idx < numMsgs;
    if (idx < n) {
      int ic=icons;
      int mlen=msgs.length;
      msg=msgs[(ic + idx) % mlen];
      for (int i=idx; i > 0; i--) {
        msgs[(ic + i) % mlen]=msgs[(ic + i - 1) % mlen];
      }
      msgs[icons]=null;
      numMsgs-=1;
      icons=(icons + 1) % mlen;
    }
 else {
      throw new IllegalStateException();
    }
  }
  return msg;
}
