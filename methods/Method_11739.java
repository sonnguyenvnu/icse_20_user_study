protected void printDefects(Enumeration<TestFailure> booBoos,int count,String type){
  if (count == 0)   return;
  if (count == 1) {
    getWriter().println("There was " + count + " " + type + ":");
  }
 else {
    getWriter().println("There were " + count + " " + type + "s:");
  }
  for (int i=1; booBoos.hasMoreElements(); i++) {
    printDefect(booBoos.nextElement(),i);
  }
}
