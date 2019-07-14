private void checkJISOGetHour(){
  int COUNT=COUNT_VERY_FAST;
  DateTime dt=new DateTime();
  for (int i=0; i < AVERAGE; i++) {
    start("JISO","getHour");
    for (int j=0; j < COUNT; j++) {
      int val=dt.getHourOfDay();
      if (val == -1) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
