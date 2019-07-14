public static void windowsThrowException(String msg){
  throw new RuntimeException(msg + " (error code = " + getLastError() + ")");
}
