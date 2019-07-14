private void checkJodaToString(){
  int COUNT=COUNT_SLOW;
  DateTime dt=new DateTime(GJChronology.getInstance());
  DateTimeFormatter f=DateTimeFormat.forPattern("dd MMM yyyy");
  for (int i=0; i < AVERAGE; i++) {
    start("Joda","toString");
    for (int j=0; j < COUNT; j++) {
      String str=dt.toString("dd MMM yyyy");
      if (str == null) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
