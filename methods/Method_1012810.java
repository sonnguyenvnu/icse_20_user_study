public static String run(int[] expectedExitCodes,String... cmd){
  try {
    ProcessBuilder pb=new ProcessBuilder(cmd);
    pb.redirectErrorStream(true);
    Process p=pb.start();
    StringBuilder output;
    try (BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()))){
      String line;
      output=new StringBuilder();
      while ((line=br.readLine()) != null) {
        output.append(line).append("\n");
      }
    }
     p.waitFor();
    boolean expected=false;
    if (expectedExitCodes != null) {
      for (      int expectedCode : expectedExitCodes) {
        if (expectedCode == p.exitValue()) {
          expected=true;
          break;
        }
      }
    }
    if (!expected) {
      LOGGER.debug("Warning: command {} returned {}",Arrays.toString(cmd),p.exitValue());
    }
    return output.toString();
  }
 catch (  Exception e) {
    LOGGER.error("Error running command " + Arrays.toString(cmd),e);
  }
  return "";
}
