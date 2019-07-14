public void populateWithRandomData(){
  Timestamp start=DateUtils.getToday();
  LinkedList<Streak> streaks=new LinkedList<>();
  for (int i=0; i < 10; i++) {
    int length=new Random().nextInt(100);
    Timestamp end=start.plus(length);
    streaks.add(new Streak(start,end));
    start=end.plus(1);
  }
  setStreaks(streaks);
}
