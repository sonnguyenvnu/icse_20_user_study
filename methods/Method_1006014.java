@Override public final Collection<T> call(final ISuggestionRequest request){
  List<T> suggestions=new ArrayList<>();
  if (!request.getUserText().isEmpty()) {
synchronized (possibleSuggestionsLock) {
      for (      T possibleSuggestion : possibleSuggestions) {
        if (isMatch(possibleSuggestion,request)) {
          suggestions.add(possibleSuggestion);
        }
      }
    }
    suggestions.sort(getComparator());
  }
  return suggestions;
}
