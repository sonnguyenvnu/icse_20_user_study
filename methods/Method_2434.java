/** 
 * ????
 * @param interval ???????????
 * @param direction ??????????interval???????
 * @return
 */
protected List<Intervalable> checkForOverlaps(Intervalable interval,Direction direction){
  List<Intervalable> overlaps=new ArrayList<Intervalable>();
  for (  Intervalable currentInterval : this.intervals) {
switch (direction) {
case LEFT:
      if (currentInterval.getStart() <= interval.getEnd()) {
        overlaps.add(currentInterval);
      }
    break;
case RIGHT:
  if (currentInterval.getEnd() >= interval.getStart()) {
    overlaps.add(currentInterval);
  }
break;
}
}
return overlaps;
}
