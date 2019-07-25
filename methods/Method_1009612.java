public void handle(Throwable ex){
  System.err.println("============= The application encountered an unrecoverable error, exiting... =============");
  ex.printStackTrace(System.err);
  System.err.println("==========================================================================================");
  System.exit(1);
}
