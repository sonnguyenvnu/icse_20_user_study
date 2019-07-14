/** 
 * Returns a Caffeine cache wrapped in a Guava  {@link Cache} facade.
 * @param builder the configured cache builder
 * @return a cache exposed under the Guava APIs
 */
@NonNull public static <K,V,K1 extends K,V1 extends V>Cache<K1,V1> build(@NonNull Caffeine<K,V> builder){
  return new CaffeinatedGuavaCache<>(builder.build());
}
