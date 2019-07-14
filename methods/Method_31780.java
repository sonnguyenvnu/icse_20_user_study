private void checkGCalSetHour(){
  int COUNT=COUNT_VERY_FAST;
  GregorianCalendar dt=new GregorianCalendar();
  for (int i=0; i < AVERAGE; i++) {
    start("GCal","setHour");
    for (int j=0; j < COUNT; j++) {
      dt.set(GregorianCalendar.HOUR_OF_DAY,13);
      if (dt == null) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
