public List<NearMiss> findNearestTo(final LoggedRequest request){
  List<StubMapping> allMappings=stubMappings.getAll();
  return sortAndTruncate(from(allMappings).transform(new Function<StubMapping,NearMiss>(){
    public NearMiss apply(    StubMapping stubMapping){
      MatchResult matchResult=stubMapping.getRequest().match(request);
      return new NearMiss(request,stubMapping,matchResult);
    }
  }
),allMappings.size());
}
