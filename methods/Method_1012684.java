/** 
 * Tries to parse  {@code dmpProfilesString} and adds the resulting{@link ProtocolInfo} instances to the {@link Set} of type{@link PanasonicDmpProfileType} in {@code deviceProtocolInfo}.
 * @param dmpProfilesString a space separated string of format profilerepresentations whose presence is to be ensured.
 * @return {@code true} if the {@link Set} of {@link ProtocolInfo} in{@code deviceProtocolInfo} changed as a result of the call.Returns  {@code false} this already contains the specifiedelements.
 */
public boolean add(String dmpProfilesString){
  if (StringUtils.isBlank(dmpProfilesString)) {
    return false;
  }
  dmpProfilesString=dmpProfilesString.replaceFirst("\\s*X-PANASONIC-DMP-Profile:\\s*","").trim();
  String[] elements=dmpProfilesString.trim().split("\\s+");
  SortedSet<ProtocolInfo> protocolInfoSet=new TreeSet<>();
  for (  String element : elements) {
    try {
      ProtocolInfo protocolInfo=dmpProfileToProtocolInfo(element);
      if (protocolInfo != null) {
        protocolInfoSet.add(protocolInfo);
      }
    }
 catch (    ParseException e) {
      LOGGER.warn("Unable to parse protocolInfo from \"{}\", this profile will not be registered: {}",element,e.getMessage());
      LOGGER.trace("",e);
    }
  }
  boolean result=false;
  if (!protocolInfoSet.isEmpty()) {
    result=deviceProtocolInfo.addAll(PANASONIC_DMP,protocolInfoSet);
  }
  populated|=result;
  return result;
}
