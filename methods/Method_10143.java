/** 
 * Gets Reddit article score.
 * @param ups   the specified vote up count
 * @param downs the specified vote down count
 * @param t     time (epoch seconds)
 * @return reddit score
 */
private static double redditArticleScore(final int ups,final int downs,final long t){
  final int x=ups - downs;
  final double z=Math.max(Math.abs(x),1);
  int y=0;
  if (x > 0) {
    y=1;
  }
 else   if (x < 0) {
    y=-1;
  }
  return Math.log10(z) + y * (t - 1353745196) / 45000;
}
