private String getNmonVersion(){
  return "[ -e \"" + getNmonFile() + "\" ] && " + getNmonFile() + " -V";
}
