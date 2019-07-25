public static Collector<TagKeyCount,TagKeyCount> reduce(final OptionalLimit limit,final OptionalLimit exactLimit){
  return groups -> {
    final List<RequestError> errors1=new ArrayList<>();
    final HashMap<String,Suggestion> suggestions=new HashMap<>();
    boolean limited=false;
    for (    final TagKeyCount g : groups) {
      errors1.addAll(g.errors);
      for (      final Suggestion s : g.suggestions) {
        final Suggestion replaced=suggestions.put(s.key,s);
        if (replaced == null) {
          continue;
        }
        final Optional<Set<String>> exactValues;
        if (s.exactValues.isPresent() && replaced.exactValues.isPresent()) {
          exactValues=Optional.<Set<String>>of(ImmutableSet.copyOf(Iterables.concat(s.exactValues.get(),replaced.exactValues.get()))).filter(set -> !exactLimit.isGreater(set.size()));
        }
 else {
          exactValues=Optional.empty();
        }
        suggestions.put(s.key,new Suggestion(s.key,s.count + replaced.count,exactValues));
      }
      limited=limited || g.limited;
    }
    final List<Suggestion> list=new ArrayList<>(suggestions.values());
    Collections.sort(list);
    return new TagKeyCount(errors1,limit.limitList(list),limited || limit.isGreater(list.size()));
  }
;
}
