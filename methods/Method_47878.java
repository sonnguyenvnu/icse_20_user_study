/** 
 * Returns the total number of repetitions for each month, from the first repetition until today, grouped by day of week. <p> The repetitions are returned in a HashMap. The key is the timestamp for the first day of the month, at midnight (00:00). The value is an integer array with 7 entries. The first entry contains the total number of repetitions during the specified month that occurred on a Saturday. The second entry corresponds to Sunday, and so on. If there are no repetitions during a certain month, the value is null.
 * @return total number of repetitions by month versus day of week
 */
@NonNull public HashMap<Timestamp,Integer[]> getWeekdayFrequency(){
  List<Repetition> reps=getByInterval(Timestamp.ZERO,DateUtils.getToday());
  HashMap<Timestamp,Integer[]> map=new HashMap<>();
  for (  Repetition r : reps) {
    Calendar date=r.getTimestamp().toCalendar();
    int weekday=r.getTimestamp().getWeekday();
    date.set(Calendar.DAY_OF_MONTH,1);
    Timestamp timestamp=new Timestamp(date.getTimeInMillis());
    Integer[] list=map.get(timestamp);
    if (list == null) {
      list=new Integer[7];
      Arrays.fill(list,0);
      map.put(timestamp,list);
    }
    list[weekday]++;
  }
  return map;
}
