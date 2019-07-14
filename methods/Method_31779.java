private void checkDateSetGetYear(){
  int COUNT=COUNT_FAST;
  Date dt=new Date();
  for (int i=0; i < AVERAGE; i++) {
    start("Date","setGetYear");
    for (int j=0; j < COUNT; j++) {
      dt.setYear(1972);
      int val=dt.getYear();
      if (val < 0) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
