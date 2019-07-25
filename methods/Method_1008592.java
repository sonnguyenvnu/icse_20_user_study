protected final String unrecognized(final RestRequest request,final Set<String> invalids,final Set<String> candidates,final String detail){
  StringBuilder message=new StringBuilder(String.format(Locale.ROOT,"request [%s] contains unrecognized %s%s: ",request.path(),detail,invalids.size() > 1 ? "s" : ""));
  boolean first=true;
  for (  final String invalid : invalids) {
    final LevensteinDistance ld=new LevensteinDistance();
    final List<Tuple<Float,String>> scoredParams=new ArrayList<>();
    for (    final String candidate : candidates) {
      final float distance=ld.getDistance(invalid,candidate);
      if (distance > 0.5f) {
        scoredParams.add(new Tuple<>(distance,candidate));
      }
    }
    CollectionUtil.timSort(scoredParams,(a,b) -> {
      int compare=a.v1().compareTo(b.v1());
      if (compare != 0)       return -compare;
 else       return a.v2().compareTo(b.v2());
    }
);
    if (first == false) {
      message.append(", ");
    }
    message.append("[").append(invalid).append("]");
    final List<String> keys=scoredParams.stream().map(Tuple::v2).collect(Collectors.toList());
    if (keys.isEmpty() == false) {
      message.append(" -> did you mean ");
      if (keys.size() == 1) {
        message.append("[").append(keys.get(0)).append("]");
      }
 else {
        message.append("any of ").append(keys.toString());
      }
      message.append("?");
    }
    first=false;
  }
  return message.toString();
}
