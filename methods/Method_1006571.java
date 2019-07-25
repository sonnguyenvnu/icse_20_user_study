@Override public void run(){
  System.out.printf("verbose: %s, printGCDetails: %s, useG1GC: %s%n",verbose,printGCDetails,useG1GC);
  System.out.println("System file encoding: " + System.getProperty("file.encoding"));
}
