private void checkJISOConstructor1(){
  int COUNT=COUNT_SLOW;
  DateTime dt=new DateTime();
  int count=0;
  for (int i=0; i < AVERAGE; i++) {
    start("JISO","new()");
    for (int j=0; j < COUNT; j++) {
      dt=new DateTime();
      if (count++ < 0) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
