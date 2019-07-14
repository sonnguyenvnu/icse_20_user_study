/** 
 * Constructs a new  {@code Caffeine} instance with the settings specified in {@code spec}.
 * @param spec the specification to build from
 * @return a new instance with the specification's settings
 */
@NonNull public static Caffeine<Object,Object> from(CaffeineSpec spec){
  Caffeine<Object,Object> builder=spec.toBuilder();
  builder.strictParsing=false;
  return builder;
}
