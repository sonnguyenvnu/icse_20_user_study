/** 
 * Return the methods as a map keyed by their common declaration types.
 * @param methods
 * @return methods grouped by declaring type name
 */
public static Map<String,List<Method>> asMethodGroupsByTypeName(Method[] methods){
  Map<String,List<Method>> methodGroups=new HashMap<>(methods.length);
  for (int i=0; i < methods.length; i++) {
    String clsName=asShortestName(methods[i].getDeclaringClass());
    if (!methodGroups.containsKey(clsName)) {
      methodGroups.put(clsName,new ArrayList<Method>());
    }
    methodGroups.get(clsName).add(methods[i]);
  }
  return methodGroups;
}
