public void add(URI uri){
  if (current == null) {
    forward.clear();
    current=uri;
    return;
  }
  if (current.equals(uri)) {
    return;
  }
  if (uri.getPath().toString().equals(current.getPath().toString())) {
    if ((uri.getFragment() == null) && (uri.getQuery() == null)) {
    }
 else     if ((current.getFragment() == null) && (current.getQuery() == null)) {
      current=uri;
    }
 else {
      forward.clear();
      backward.add(current);
      current=uri;
    }
    return;
  }
  if (uri.toString().startsWith(current.toString())) {
    current=uri;
    return;
  }
  if (current.toString().startsWith(uri.toString())) {
    return;
  }
  forward.clear();
  backward.add(current);
  current=uri;
}
