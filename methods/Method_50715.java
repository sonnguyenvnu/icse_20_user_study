public boolean hasPrefixesOrSuffixes(){
  for (  PropertyDescriptor<List<String>> desc : suffixOrPrefixProperties()) {
    if (!getProperty(desc).isEmpty()) {
      return true;
    }
  }
  return false;
}
