/** 
 * Returns all profiles names.
 */
public String[] getAllProfiles(){
  String[] profiles=new String[data.profileProperties.size()];
  int index=0;
  for (  String profileName : data.profileProperties.keySet()) {
    profiles[index]=profileName;
    index++;
  }
  return profiles;
}
