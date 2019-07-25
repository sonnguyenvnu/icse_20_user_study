/** 
 * ???????,??"777"?
 */
public static void chmod(String path,String mode){
  try {
    String command="chmod " + mode + " " + path;
    Runtime runtime=Runtime.getRuntime();
    runtime.exec(command);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
