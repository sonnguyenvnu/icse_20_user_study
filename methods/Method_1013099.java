public void add(JoinBytesInt j){
  refs=refs + j.refs;
  if (0 == ulen) {
    ulen=j.ulen;
    url=j.url;
  }
}
