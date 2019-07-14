/** 
 * ???
 * @return
 */
public long getHitPercent(){
  long total=hits + misses;
  if (total == 0) {
    return 0;
  }
 else {
    NumberFormat formatter=new DecimalFormat("0");
    return NumberUtils.toLong(formatter.format(hits * 100.0 / total));
  }
}
