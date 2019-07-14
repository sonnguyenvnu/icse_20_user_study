private void checkJodaGetMonth(){
  int COUNT=COUNT_VERY_FAST;
  DateTime dt=new DateTime(GJChronology.getInstance());
  for (int i=0; i < AVERAGE; i++) {
    start("Joda","getMonth");
    for (int j=0; j < COUNT; j++) {
      int val=dt.getMonthOfYear();
      if (val == 0) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
