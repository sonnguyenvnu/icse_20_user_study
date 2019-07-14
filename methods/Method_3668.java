/** 
 * ????????Enum??
 * @param name ????
 * @param customNatureCollector ??????
 * @return ????
 */
public static Nature convertStringToNature(String name,LinkedHashSet<Nature> customNatureCollector){
  Nature nature=Nature.fromString(name);
  if (nature == null) {
    nature=Nature.create(name);
    if (customNatureCollector != null)     customNatureCollector.add(nature);
  }
  return nature;
}
