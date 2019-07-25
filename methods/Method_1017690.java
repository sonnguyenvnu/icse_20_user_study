@NotNull public SuggestionList wrap(@Nullable String prefix,@Nullable String suffix){
  SuggestionList prefixedList;
  if ((prefix == null || prefix.isEmpty()) && (suffix == null || suffix.isEmpty())) {
    prefixedList=new SuggestionList(this);
  }
 else {
    prefixedList=new SuggestionList(this.project);
    if (prefix == null)     prefix="";
    if (suffix == null)     suffix="";
    for (    Suggestion suggestion : suggestions) {
      prefixedList.add(prefix + suggestion.getText() + suffix,suggestion);
    }
  }
  return prefixedList;
}
