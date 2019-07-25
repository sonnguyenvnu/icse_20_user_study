public static Collector<TagValuesSuggest,TagValuesSuggest> reduce(final OptionalLimit limit,final OptionalLimit groupLimit){
  return groups -> {
    final ImmutableList.Builder<RequestError> errors=ImmutableList.builder();
    final Map<String,MidFlight> midflights=new HashMap<>();
    boolean limited1=false;
    for (    final TagValuesSuggest g : groups) {
      errors.addAll(g.getErrors());
      for (      final Suggestion s : g.suggestions) {
        MidFlight m=midflights.get(s.key);
        if (m == null) {
          m=new MidFlight();
          midflights.put(s.key,m);
        }
        m.values.addAll(s.values);
        m.limited=m.limited || s.limited;
      }
      limited1=limited1 || g.limited;
    }
    final SortedSet<Suggestion> suggestions1=new TreeSet<>();
    for (    final Map.Entry<String,MidFlight> e : midflights.entrySet()) {
      final String key=e.getKey();
      final MidFlight m=e.getValue();
      final boolean sLimited=m.limited || groupLimit.isGreater(m.values.size());
      suggestions1.add(new Suggestion(key,groupLimit.limitSortedSet(m.values),sLimited));
    }
    return new TagValuesSuggest(errors.build(),limit.limitList(ImmutableList.copyOf(suggestions1)),limited1 || limit.isGreater(suggestions1.size()));
  }
;
}
