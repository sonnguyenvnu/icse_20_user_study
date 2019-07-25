/** 
 * @param type type
 * @return a Dot11VenueInfo object.
 */
public static Dot11VenueInfo register(Dot11VenueInfo type){
  return registry.put(type.value(),type);
}
