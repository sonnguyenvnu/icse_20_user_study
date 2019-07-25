static final public void report(String msg,Throwable t){
  System.err.println(msg);
  System.err.println("Reported exception:");
  t.printStackTrace();
}
