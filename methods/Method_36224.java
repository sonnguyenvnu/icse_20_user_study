private static List<NearMiss> sortAndTruncate(FluentIterable<NearMiss> nearMisses,int originalSize){
  return nearMisses.toSortedList(NEAR_MISS_ASCENDING_COMPARATOR).subList(0,min(NEAR_MISS_COUNT,originalSize));
}
