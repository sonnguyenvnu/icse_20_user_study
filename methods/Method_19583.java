public void reset(View v){
  final Calendar calendar=Calendar.getInstance();
  final long now=calendar.getTimeInMillis();
  calendar.set(Calendar.HOUR_OF_DAY,0);
  calendar.set(Calendar.MINUTE,0);
  final long midnight=calendar.getTimeInMillis();
  final int sinceMidnight=(int)(now - midnight);
  setTime(sinceMidnight);
}
