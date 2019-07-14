/** 
 * Parses arguments passed to the runtime environment for debug flags <p> Options specified in: <ul> <li> <a href="http://docs.oracle.com/javase/6/docs/technotes/guides/jpda/conninv.html#Invocation" >javase-6</a></li> <li><a href="http://docs.oracle.com/javase/7/docs/technotes/guides/jpda/conninv.html#Invocation" >javase-7</a></li> <li><a href="http://docs.oracle.com/javase/8/docs/technotes/guides/jpda/conninv.html#Invocation" >javase-8</a></li>
 * @param arguments the arguments passed to the runtime environment, usually this will be  {@link RuntimeMXBean#getInputArguments()}
 * @return true if the current JVM was started in debug mode, falseotherwise.
 */
private static boolean isDebugging(List<String> arguments){
  for (  final String argument : arguments) {
    if ("-Xdebug".equals(argument) || argument.startsWith("-agentlib:jdwp")) {
      return true;
    }
  }
  return false;
}
