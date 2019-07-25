@Override public Path resolve(Path other){
  if (other.isAbsolute()) {
    return other;
  }
 else   if (other.getNameCount() == 0) {
    return this;
  }
 else {
    List<String> elements=new ArrayList<>(this.elements);
    for (int idx=0; idx < other.getNameCount(); idx++) {
      elements.add(other.getName(idx).toString());
    }
    return new JcrPath(this.absolute,elements);
  }
}
