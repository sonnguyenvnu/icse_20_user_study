@Override public Path subpath(int beginIndex,int endIndex){
  if (beginIndex < 0 || beginIndex > this.elements.size() - 1 || endIndex < beginIndex || endIndex > this.elements.size()) {
    throw new IllegalArgumentException("Invalid indices bounds: " + beginIndex + ", " + endIndex);
  }
 else {
    return new JcrPath(this.isAbsolute() && beginIndex == 0,this.elements.subList(beginIndex,endIndex));
  }
}
