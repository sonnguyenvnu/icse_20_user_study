/** 
 * ???
 * @return
 */
public String getHitPercent(){
  long totalHits=hits + misses;
  if (totalHits <= 0) {
    return "?????";
  }
  double percent=100 * (double)hits / totalHits;
  DecimalFormat df=new DecimalFormat("##.##");
  return df.format(percent) + "%";
}
