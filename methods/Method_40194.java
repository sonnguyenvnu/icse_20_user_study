@NotNull public String extendPath(@NotNull String name){
  name=_.moduleName(name);
  if (path.equals("")) {
    return name;
  }
  return path + "." + name;
}
