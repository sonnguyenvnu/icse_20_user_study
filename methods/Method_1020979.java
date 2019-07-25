public String exec(String command){
  BufferedOutputStream bufferedOutputStream=null;
  BufferedInputStream bufferedInputStream=null;
  Process process=null;
  try {
    process=Runtime.getRuntime().exec("sh");
    bufferedOutputStream=new BufferedOutputStream(process.getOutputStream());
    bufferedInputStream=new BufferedInputStream(process.getInputStream());
    bufferedOutputStream.write(command.getBytes());
    bufferedOutputStream.write('\n');
    bufferedOutputStream.flush();
    bufferedOutputStream.close();
    process.waitFor();
    String outputStr=getStrFromBufferInputSteam(bufferedInputStream);
    return outputStr;
  }
 catch (  Exception e) {
    return null;
  }
 finally {
    if (bufferedOutputStream != null) {
      try {
        bufferedOutputStream.close();
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
    }
    if (bufferedInputStream != null) {
      try {
        bufferedInputStream.close();
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
    }
    if (process != null) {
      process.destroy();
    }
  }
}
