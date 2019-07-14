void parseParameters(String[] args){
  for (  String arg : args) {
    try {
      classes.add(Classes.getClass(arg));
    }
 catch (    ClassNotFoundException e) {
      parserErrors.add(new IllegalArgumentException("Could not find class [" + arg + "]",e));
    }
  }
}
