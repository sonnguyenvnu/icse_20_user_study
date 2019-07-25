/** 
 * Based on the statistics it applies expert rules to help tuning the  {@link org.roaringbitmap.RoaringBitmapWriter}
 * @param s statistics
 * @return some message
 */
public static String recommend(BitmapStatistics s){
  if (s.containerCount() == 0) {
    return "Empty statistics, cannot recommend.";
  }
  StringBuilder sb=new StringBuilder();
  containerCountRecommendations(s,sb);
  double acFraction=s.containerFraction(s.getArrayContainersStats().getContainersCount());
  if (acFraction > ArrayContainersDomination) {
    if (s.getArrayContainersStats().averageCardinality() < WorthUsingArraysCardinalityThreshold) {
      arrayContainerRecommendations(s,sb);
    }
 else {
      denseArrayWarning(sb);
      constantMemoryRecommendation(s,sb);
    }
  }
 else   if (s.containerFraction(s.getRunContainerCount()) > RunContainersDomination) {
    runContainerRecommendations(sb);
  }
 else {
    constantMemoryRecommendation(s,sb);
  }
  return sb.toString();
}
