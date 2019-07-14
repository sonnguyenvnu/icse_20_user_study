public static void abort(Node loc,String msg){
  System.err.println(loc.getFileLineCol() + " " + msg);
  System.err.flush();
  Thread.dumpStack();
  System.exit(1);
}
