/** 
 * @return only suggestions of type <code>suggestionType</code> contained in this {@link Suggest} instance
 */
public <T extends Suggestion>List<T> filter(Class<T> suggestionType){
  return suggestions.stream().filter(suggestion -> suggestion.getClass() == suggestionType).map(suggestion -> (T)suggestion).collect(Collectors.toList());
}
