public void record(boolean status){
  if (status) {
    int weight=currentWeight * 2;
    currentWeight=Math.min(weight,MAX_WEIGHT);
  }
 else {
    int weight=currentWeight / 2;
    currentWeight=Math.max(weight,MIN_WEIGHT);
  }
}
