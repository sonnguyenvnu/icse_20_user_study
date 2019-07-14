private String trimAnyPathSep(String name){
  return name.startsWith(FILE_SEPARATOR) ? name.substring(1) : name;
}
