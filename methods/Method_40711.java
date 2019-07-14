@NotNull public String extendPath(@NotNull String name,String sep){
  name=_.mainName(name);
  if (path.equals("")) {
    return name;
  }
 else {
    return path + sep + name;
  }
}
