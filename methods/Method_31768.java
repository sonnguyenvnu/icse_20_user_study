private void checkJodaConstructor2(){
  int COUNT=COUNT_VERY_FAST;
  DateTime dt=new DateTime(12345L,GJChronology.getInstance());
  for (int i=0; i < AVERAGE; i++) {
    start("Joda","new(millis)");
    for (int j=0; j < COUNT; j++) {
      dt=new DateTime(12345L,GJChronology.getInstance());
      if (dt == null) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
