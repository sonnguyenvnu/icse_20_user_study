private void checkDateConstructor1(){
  int COUNT=COUNT_SLOW;
  Date dt=new Date();
  int count=0;
  for (int i=0; i < AVERAGE; i++) {
    start("Date","new()");
    for (int j=0; j < COUNT; j++) {
      dt=new Date();
      if (count++ < 0) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
