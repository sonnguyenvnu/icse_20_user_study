static public void loge(String message,Throwable e){
  if (Base.DEBUG) {
    if (message != null) {
      System.err.println(message);
    }
    e.printStackTrace();
  }
}
