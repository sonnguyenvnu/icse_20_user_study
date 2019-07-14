/** 
 * ??? ? ??????? ??
 */
public static int getDaysByYearMonth(int year,int month){
  Calendar a=Calendar.getInstance();
  a.set(Calendar.YEAR,year);
  a.set(Calendar.MONTH,month - 1);
  a.set(Calendar.DATE,1);
  a.roll(Calendar.DATE,-1);
  int maxDate=a.get(Calendar.DATE);
  return maxDate;
}
