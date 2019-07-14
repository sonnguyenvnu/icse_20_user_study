private void checkDateSetHour(){
  int COUNT=COUNT_FAST;
  Date dt=new Date();
  for (int i=0; i < AVERAGE; i++) {
    start("Date","setHour");
    for (int j=0; j < COUNT; j++) {
      dt.setHours(13);
      if (dt == null) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
