/** 
 * Maps profile type into stripped profile type data transfer object.
 * @param profileType the profile type to be mapped
 * @return the profile type DTO object
 */
public static ProfileTypeDTO map(ProfileType profileType){
  return new ProfileTypeDTO(profileType.getUID().toString(),profileType.getLabel(),profileType instanceof TriggerProfileType ? "TRIGGER" : "STATE",profileType.getSupportedItemTypes());
}
