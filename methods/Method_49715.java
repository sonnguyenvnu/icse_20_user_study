@Override public void showSuggestions(List<String> suggestions){
  mSuggestionsAdapter.clear();
  mSuggestionsAdapter.addAll(suggestions);
  mSuggestionsAdapter.notifyDataSetChanged();
}
