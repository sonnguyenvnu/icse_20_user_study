/** 
 * Take all arguments as a list with the given type.
 */
public <T extends Expression>List<T> all(final Class<T> expected){
  final List<T> result=ImmutableList.copyOf(args.stream().map(v -> v.cast(expected)).iterator());
  args.clear();
  return result;
}
