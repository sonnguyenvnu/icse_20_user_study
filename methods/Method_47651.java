public void populateWithRandomData(){
  Random random=new Random();
  scores=new LinkedList<>();
  double previous=0.5f;
  Timestamp timestamp=DateUtils.getToday();
  for (int i=1; i < 100; i++) {
    double step=0.1f;
    double current=previous + random.nextDouble() * step * 2 - step;
    current=Math.max(0,Math.min(1.0f,current));
    scores.add(new Score(timestamp.minus(i),current));
    previous=current;
  }
}
