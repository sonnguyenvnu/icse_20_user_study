private void checkDateConstructor2(){
  int COUNT=COUNT_VERY_FAST;
  Date dt=new Date();
  for (int i=0; i < AVERAGE; i++) {
    start("Date","new(millis)");
    for (int j=0; j < COUNT; j++) {
      dt=new Date(12345L);
      if (dt == null) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
