private void setCalendar(char y0,char y1,char y2,char y3,char M0,char M1,char d0,char d1){
  calendar=Calendar.getInstance(timeZone,locale);
  int year=(y0 - '0') * 1000 + (y1 - '0') * 100 + (y2 - '0') * 10 + (y3 - '0');
  int month=(M0 - '0') * 10 + (M1 - '0') - 1;
  int day=(d0 - '0') * 10 + (d1 - '0');
  calendar.set(Calendar.YEAR,year);
  calendar.set(Calendar.MONTH,month);
  calendar.set(Calendar.DAY_OF_MONTH,day);
}
