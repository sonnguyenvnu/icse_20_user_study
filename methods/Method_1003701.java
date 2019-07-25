private static boolean init(){
  try {
    Slf4jNoBindingDetector.class.getClassLoader().loadClass(CLASS_NAME);
    return true;
  }
 catch (  ClassNotFoundException e) {
    System.err.println("WARNING: No slf4j logging binding found for Ratpack, there will be no logging output." + System.lineSeparator() + "WARNING: Please add an slf4j binding, such as slf4j-log4j2, to the classpath." + System.lineSeparator() + "WARNING: More info may be found here: http://ratpack.io/manual/current/logging.html");
    return false;
  }
}
