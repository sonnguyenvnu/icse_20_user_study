@Override public Collection<String> call(AutoCompletionBinding.ISuggestionRequest request){
  List<String> suggestions=new ArrayList<>();
  if (suggestionProvider != null) {
    suggestions.addAll(suggestionProvider.call(request));
  }
  suggestions.addAll(contentSelectorValues);
  return suggestions;
}
