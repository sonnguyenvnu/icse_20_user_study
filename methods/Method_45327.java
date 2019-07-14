public static String resourceRoot(final String name){
  if (name.startsWith(SEPARATOR)) {
    return name;
  }
  return SEPARATOR + name;
}
