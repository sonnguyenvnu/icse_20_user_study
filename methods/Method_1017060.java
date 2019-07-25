public static Collector<FindTags,FindTags> reduce(){
  return results -> {
    final List<RequestError> errors=new ArrayList<>();
    final HashMap<String,Set<String>> tags=new HashMap<>();
    int size=0;
    for (    final FindTags r : results) {
      errors.addAll(r.errors);
      updateTags(tags,r.tags);
      size+=r.getSize();
    }
    return new FindTags(errors,tags,size);
  }
;
}
