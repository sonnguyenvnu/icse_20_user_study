protected String prepend(final String prefix,final String string){
  if (!string.startsWith(prefix)) {
    return prefix + string;
  }
  return string;
}
