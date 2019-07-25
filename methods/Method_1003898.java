/** 
 * A convenient version of  {@link #exposing(Iterable,com.google.inject.Module)} when you justwant to expose a single binding.
 */
public static Module exposing(Key<?> key,Module module){
  return exposing(ImmutableList.of(key),module);
}
