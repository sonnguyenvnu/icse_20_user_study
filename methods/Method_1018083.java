@Override public Path relativize(Path other){
  if (other.isAbsolute() != isAbsolute() || other.getNameCount() < getNameCount()) {
    return other;
  }
 else   if (other.getNameCount() == 0) {
    return this;
  }
 else {
    int idx=0;
    for (; idx < getNameCount(); idx++) {
      if (!other.getName(idx).equals(getName(idx))) {
        return other;
      }
    }
    return other.subpath(idx - 1,other.getNameCount());
  }
}
