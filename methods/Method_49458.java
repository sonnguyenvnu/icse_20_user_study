@Override public Stream<RawQuery.Result<String>> getResults(){
  return hits.getHits().stream().map(hit -> new RawQuery.Result<>(hit.getId(),hit.getScore() != null ? hit.getScore() : 0f));
}
