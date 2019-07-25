public static void load(Errors errs) throws AbortException {
  Configuration Parser;
  try {
    errors=errs;
    File source=new File("config.src");
    String origin;
    if (source.exists()) {
      input=new java.io.FileReader(source);
      origin=" from local config.src file.";
    }
 else {
      input=new java.io.StringReader(defaultConfig);
      origin=" from defaults.";
    }
    Parser=new Configuration(input);
    try {
      Parser.ConfigurationUnit();
    }
 catch (    ParseException e) {
      errors.addAbort(Location.nullLoc,"\nConfiguration Parser:  Encountered errors during parse.  " + e.getMessage(),true);
    }
  }
 catch (  java.io.FileNotFoundException e) {
    errors.addAbort(Location.nullLoc,"File not found.\n" + e,true);
  }
}
