/** 
 * Return the name of the type in its short form if its known to us otherwise return its name fully packaged.
 * @param type
 * @return String
 */
public static String asShortestName(Class<?> type){
  String name=SHORT_NAMES_BY_TYPE.get(type);
  return name == null ? type.getName() : name;
}
