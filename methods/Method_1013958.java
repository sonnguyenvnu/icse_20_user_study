/** 
 * Creates a new builder for a given thing UID.
 * @param thingUID the thing UID for which the builder should be created-
 * @return a new instance of a {@link DiscoveryResultBuilder}
 */
public static DiscoveryResultBuilder create(ThingUID thingUID){
  return new DiscoveryResultBuilder(thingUID);
}
