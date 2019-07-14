/** 
 * Collect results of the <code>dumpsys meminfo</code> command restricted to this application process.
 * @return The execution result.
 */
@Nullable private String collectMemInfo(){
  try {
    final List<String> commandLine=new ArrayList<>();
    commandLine.add("dumpsys");
    commandLine.add("meminfo");
    commandLine.add(Integer.toString(android.os.Process.myPid()));
    final Process process=Runtime.getRuntime().exec(commandLine.toArray(new String[commandLine.size()]));
    return new StreamReader(process.getInputStream()).read();
  }
 catch (  IOException e) {
    ACRA.log.e(LOG_TAG,"MemoryInfoCollector.meminfo could not retrieve data",e);
    return null;
  }
}
