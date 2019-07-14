private void checkDateConstructor3(){
  int COUNT=COUNT_SLOW;
  Date dt=new Date();
  for (int i=0; i < AVERAGE; i++) {
    start("Date","new(YMD)");
    for (int j=0; j < COUNT; j++) {
      dt=new Date(1972,10,1);
      if (dt == null) {
        System.out.println("Anti optimise");
      }
    }
    end(COUNT);
  }
}
