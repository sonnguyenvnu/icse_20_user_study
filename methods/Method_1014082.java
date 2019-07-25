/** 
 * Indicates whether two  {@link Thing}s are technical equal.
 * @param a Thing object
 * @param b another Thing object
 * @return true whether a and b are equal, otherwise false
 */
public static boolean equals(Thing a,Thing b){
  if (!a.getUID().equals(b.getUID())) {
    return false;
  }
  if (!Objects.equals(a.getBridgeUID(),b.getBridgeUID())) {
    return false;
  }
  if (!Objects.equals(a.getConfiguration(),b.getConfiguration())) {
    return false;
  }
  if (!Objects.equals(a.getLabel(),b.getLabel())) {
    return false;
  }
  if (!Objects.equals(a.getLocation(),b.getLocation())) {
    return false;
  }
  List<Channel> channelsOfA=a.getChannels();
  List<Channel> channelsOfB=b.getChannels();
  if (channelsOfA.size() != channelsOfB.size()) {
    return false;
  }
  if (!toString(channelsOfA).equals(toString(channelsOfB))) {
    return false;
  }
  return true;
}
