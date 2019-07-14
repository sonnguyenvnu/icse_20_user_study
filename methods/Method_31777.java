private void checkDateSetYear(){
  int COUNT=COUNT_FAST;
  Date dt=new Date();
  for (int i=0; i < AVERAGE; i++) {
    start("Date","setYear");
    for (int j=0; j < COUNT; j++) {
      dt.setYear(1972);
      if (dt == null) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
