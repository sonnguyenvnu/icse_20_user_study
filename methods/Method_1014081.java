/** 
 * Create this builder with all properties from the given  {@link ThingType}.
 * @param thingType take all properties from this {@link ThingType}.
 * @return a new {@link ThingTypeBuilder} configured with all properties from the given {@link ThingType};
 * @return the new {@link ThingTypeBuilder}.
 */
public static ThingTypeBuilder instance(ThingType thingType){
  return new ThingTypeBuilder(thingType);
}
