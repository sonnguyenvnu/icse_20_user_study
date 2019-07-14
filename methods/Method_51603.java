/** 
 * Return the methods as a map keyed by their common declaration types.
 * @param methods
 * @return methods grouped by declaring type name
 */
public static Map<String,List<Method>> asMethodGroupsByTypeName(List<Method> methods){
  Map<String,List<Method>> methodGroups=new HashMap<>(methods.size());
  for (  Method m : methods) {
    String clsName=asShortestName(m.getDeclaringClass());
    if (!methodGroups.containsKey(clsName)) {
      methodGroups.put(clsName,new ArrayList<Method>());
    }
    methodGroups.get(clsName).add(m);
  }
  return methodGroups;
}
