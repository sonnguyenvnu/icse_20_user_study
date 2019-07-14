public void populateWithRandomData(){
  Random random=new Random();
  List<Checkmark> checkmarks=new LinkedList<>();
  Timestamp today=DateUtils.getToday();
  for (int i=1; i < 100; i++) {
    int value=random.nextInt(1000);
    checkmarks.add(new Checkmark(today.minus(i),value));
  }
  setCheckmarks(checkmarks);
  setTarget(0.5);
}
