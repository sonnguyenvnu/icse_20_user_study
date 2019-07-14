private void dumpObjs(Object[][] objs,PrintStream out){
  for (int i=0; i < objs.length; ++i) {
    for (int j=0; j < objs[i].length; ++j) {
      out.println(i + " " + j + " " + objs[i][j]);
    }
  }
}
