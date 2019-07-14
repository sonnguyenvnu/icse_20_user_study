public List<NearMiss> findAllNearMissesFor(RequestPatternBuilder requestPatternBuilder){
  FindNearMissesResult nearMissesResult=admin.findTopNearMissesFor(requestPatternBuilder.build());
  return nearMissesResult.getNearMisses();
}
