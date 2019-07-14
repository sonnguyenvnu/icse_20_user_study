private void checkJodaConstructor3(){
  int COUNT=COUNT_SLOW;
  DateTime dt=new DateTime(1972,10,1,0,0,0,0,GJChronology.getInstance());
  for (int i=0; i < AVERAGE; i++) {
    start("Joda","new(YMD)");
    for (int j=0; j < COUNT; j++) {
      dt=new DateTime(1972,10,1,0,0,0,0,GJChronology.getInstance());
      if (dt == null) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
