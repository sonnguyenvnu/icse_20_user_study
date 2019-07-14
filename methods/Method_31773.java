private void checkJISOGetYear(){
  int COUNT=COUNT_VERY_FAST;
  DateTime dt=new DateTime();
  for (int i=0; i < AVERAGE; i++) {
    start("JISO","getYear");
    for (int j=0; j < COUNT; j++) {
      int val=dt.getYear();
      if (val == 0) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
