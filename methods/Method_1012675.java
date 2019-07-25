/** 
 * Tries to parse  {@code protocolInfoString} and add the resulting{@link ProtocolInfo} instances.
 * @param type The {@link DeviceProtocolInfoSource} that identifies thesource of these  {@code protocolInfo}s.
 * @param protocolInfoString a comma separated string of{@code protocolInfo} representations whose presence is to beensured.
 * @return {@code true} if this changed as a result of the call. Returns{@code false} this already contains the specified element(s).
 */
public boolean add(DeviceProtocolInfoSource<?> type,String protocolInfoString){
  if (StringUtils.isBlank(protocolInfoString)) {
    return false;
  }
  String[] elements=protocolInfoString.trim().split(COMMA_SPLIT_REGEX);
  boolean result=false;
  setsLock.writeLock().lock();
  try {
    SortedSet<ProtocolInfo> currentSet;
    if (protocolInfoSets.containsKey(type)) {
      currentSet=protocolInfoSets.get(type);
    }
 else {
      currentSet=new TreeSet<ProtocolInfo>();
      protocolInfoSets.put(type,currentSet);
    }
    SortedSet<ProtocolInfo> tempSet=null;
    for (    String element : elements) {
      try {
        tempSet=handleSpecialCaseString(element);
        if (tempSet == null) {
          result|=currentSet.add(new ProtocolInfo(unescapeString(element)));
        }
 else {
          result|=currentSet.addAll(tempSet);
          tempSet=null;
        }
      }
 catch (      ParseException e) {
        LOGGER.warn("Unable to parse protocolInfo from \"{}\", this profile will not be registered: {}",element,e.getMessage());
        LOGGER.trace("",e);
      }
    }
    updateImageProfiles();
  }
  finally {
    setsLock.writeLock().unlock();
  }
  return result;
}
