final void set(Iterable<? extends Entry<? extends CharSequence,String>> headers){
  requireNonNull(headers,"headers");
  if (headers == this) {
    return;
  }
  for (  Entry<? extends CharSequence,String> e : headers) {
    remove(e.getKey());
  }
  if (!addFast(headers)) {
    addSlow(headers);
  }
}
