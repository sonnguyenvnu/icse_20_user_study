private void checkJodaConstructor1(){
  int COUNT=COUNT_SLOW;
  DateTime dt=new DateTime(GJChronology.getInstance());
  int count=0;
  for (int i=0; i < AVERAGE; i++) {
    start("Joda","new()");
    for (int j=0; j < COUNT; j++) {
      dt=new DateTime(GJChronology.getInstance());
      if (count++ < 0) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
