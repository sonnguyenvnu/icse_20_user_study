public String getFullSourceLine(){
  return importKw + " " + (isStatic ? (staticKw + " ") : "") + packageName + "." + className + ";";
}
