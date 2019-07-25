public static DayAsDate today(){
  final Date now=new Date();
  final int year=now.getYear() + 1900;
  final int month=now.getMonth() + 1;
  final int dayOfMonth=now.getDate();
  return create(year,month,dayOfMonth);
}
