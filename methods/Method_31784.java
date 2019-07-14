private void checkJISOToString(){
  int COUNT=COUNT_SLOW;
  DateTime dt=new DateTime();
  DateTimeFormatter f=DateTimeFormat.forPattern("dd MMM yyyy");
  for (int i=0; i < AVERAGE; i++) {
    start("JISO","toString");
    for (int j=0; j < COUNT; j++) {
      String str=dt.toString("dd MMM yyyy");
      if (str == null) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
