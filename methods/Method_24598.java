static public String parseTopLevelClassName(String name){
  int dollar=name.indexOf('$');
  return (dollar == -1) ? name : name.substring(0,dollar);
}
