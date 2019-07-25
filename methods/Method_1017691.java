@NotNull public SuggestionListFail wrap(@Nullable SuggestionListFail prefixes,@Nullable SuggestionListFail suffixes){
  SuggestionListFail wrappedList=new SuggestionListFail();
  if ((prefixes == null || prefixes.isEmpty()) && (suffixes == null || suffixes.isEmpty())) {
  }
 else   if (prefixes == null || prefixes.isEmpty()) {
    for (    Suggestion suffix : suffixes.suggestions) {
      for (      Suggestion suggestion : suggestions) {
      }
    }
  }
 else   if (suffixes == null || suffixes.isEmpty()) {
    for (    Suggestion prefix : prefixes.suggestions) {
      for (      Suggestion suggestion : suggestions) {
      }
    }
  }
 else {
    for (    Suggestion prefix : prefixes.suggestions) {
      for (      Suggestion suffix : suffixes.suggestions) {
        for (        Suggestion suggestion : suggestions) {
        }
      }
    }
  }
  return wrappedList;
}
