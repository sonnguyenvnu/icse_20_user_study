public boolean isSameAs(ImportStatement is){
  return packageName.equals(is.packageName) && className.equals(is.className) && isStatic == is.isStatic;
}
