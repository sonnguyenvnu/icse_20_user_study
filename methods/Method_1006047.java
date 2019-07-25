public Collection<?> complete(AutoCompletionBinding.ISuggestionRequest request){
  return suggestionProvider.call(request);
}
