/** 
 * Maps configuration description into configuration description DTO object.
 * @param configDescription the configuration description (not null)
 * @return the configuration description DTO object
 */
public static ConfigDescriptionDTO map(ConfigDescription configDescription){
  List<ConfigDescriptionParameterGroupDTO> parameterGroups=mapParameterGroups(configDescription.getParameterGroups());
  List<ConfigDescriptionParameterDTO> parameters=mapParameters(configDescription.getParameters());
  return new ConfigDescriptionDTO(toDecodedString(configDescription.getUID()),parameters,parameterGroups);
}
