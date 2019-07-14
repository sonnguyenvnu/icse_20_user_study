/** 
 * ???interval??????
 * @param interval
 * @return
 */
public List<Intervalable> findOverlaps(Intervalable interval){
  List<Intervalable> overlaps=new ArrayList<Intervalable>();
  if (this.point < interval.getStart()) {
    addToOverlaps(interval,overlaps,findOverlappingRanges(this.right,interval));
    addToOverlaps(interval,overlaps,checkForOverlapsToTheRight(interval));
  }
 else   if (this.point > interval.getEnd()) {
    addToOverlaps(interval,overlaps,findOverlappingRanges(this.left,interval));
    addToOverlaps(interval,overlaps,checkForOverlapsToTheLeft(interval));
  }
 else {
    addToOverlaps(interval,overlaps,this.intervals);
    addToOverlaps(interval,overlaps,findOverlappingRanges(this.left,interval));
    addToOverlaps(interval,overlaps,findOverlappingRanges(this.right,interval));
  }
  return overlaps;
}
