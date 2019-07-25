public PropertyList last(){
  if (next == null) {
    return this;
  }
 else   return next.last();
}
