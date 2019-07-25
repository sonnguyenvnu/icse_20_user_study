/** 
 * Equivalent to calling  {@code from(Predicates.alwaysTrue(), Arrays.asList(sources)}.
 */
public static ArgsInfo from(Object... sources) throws IOException {
  return from(ImmutableList.copyOf(sources));
}
