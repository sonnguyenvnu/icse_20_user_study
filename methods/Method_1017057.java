public static Collector<FindKeys,FindKeys> reduce(){
  return results -> {
    final List<RequestError> errors=new ArrayList<>();
    final Set<String> keys=new HashSet<>();
    int size=0;
    int duplicates=0;
    for (    final FindKeys result : results) {
      errors.addAll(result.errors);
      for (      final String k : result.keys) {
        if (keys.add(k)) {
          duplicates+=1;
        }
      }
      duplicates+=result.getDuplicates();
      size+=result.getSize();
    }
    return new FindKeys(errors,keys,size,duplicates);
  }
;
}
