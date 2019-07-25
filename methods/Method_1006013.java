/** 
 * Create a default suggestion provider based on the toString() method of the generic objects using the provided stringConverter
 * @param stringConverter A stringConverter which converts generic T into a string
 * @param possibleSuggestions All possible suggestions
 */
public static <T>SuggestionProvider<T> create(Callback<T,String> stringConverter,Collection<T> possibleSuggestions){
  SuggestionProviderString<T> suggestionProvider=new SuggestionProviderString<>(stringConverter);
  suggestionProvider.addPossibleSuggestions(possibleSuggestions);
  return suggestionProvider;
}
