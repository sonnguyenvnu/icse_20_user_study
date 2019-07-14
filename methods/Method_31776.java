private void checkDateGetHour(){
  int COUNT=COUNT_FAST;
  Date dt=new Date();
  for (int i=0; i < AVERAGE; i++) {
    start("Date","getHour");
    for (int j=0; j < COUNT; j++) {
      int val=dt.getHours();
      if (val == -1) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
