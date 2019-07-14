private static void printUsage(){
  System.out.println("Usage: java org.joda.time.tz.ZoneInfoCompiler <options> <source files>");
  System.out.println("where possible options include:");
  System.out.println("  -src <directory>    Specify where to read source files");
  System.out.println("  -dst <directory>    Specify where to write generated files");
  System.out.println("  -verbose            Output verbosely (default false)");
}
