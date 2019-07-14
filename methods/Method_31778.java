private void checkJISOSetGetYear(){
  int COUNT=COUNT_FAST;
  DateTime dt=new DateTime();
  for (int i=0; i < AVERAGE; i++) {
    start("JISO","setGetYear");
    for (int j=0; j < COUNT; j++) {
      dt=dt.year().setCopy(1972);
      int val=dt.getYear();
      if (val < 0) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
