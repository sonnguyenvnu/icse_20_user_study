/** 
 * @return true if -Xdock:icon was specified on the command line
 */
private boolean dockIconSpecified(){
  List<String> jvmArgs=ManagementFactory.getRuntimeMXBean().getInputArguments();
  for (  String arg : jvmArgs) {
    if (arg.startsWith("-Xdock:icon")) {
      return true;
    }
  }
  return false;
}
