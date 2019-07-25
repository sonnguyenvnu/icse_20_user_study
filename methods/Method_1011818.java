public static void foo(){
  final LocalReference<LocalDate> date=new LocalReference<LocalDate>(LocalDate.parse("2001-01-01"));
  DateOperations.plusAssign(date,1);
  System.out.println(DateOperations.plus(date.value,1));
}
