/** 
 * Returns all methods. Cached. Lazy.
 */
public MethodDescriptor[] getAllMethodDescriptors(){
  if (allMethods == null) {
    final List<MethodDescriptor> allMethodsList=new ArrayList<>();
    for (    MethodDescriptor[] methodDescriptors : methodsMap.values()) {
      Collections.addAll(allMethodsList,methodDescriptors);
    }
    final MethodDescriptor[] allMethods=allMethodsList.toArray(new MethodDescriptor[0]);
    Arrays.sort(allMethods,Comparator.comparing(md -> md.getMethod().getName()));
    this.allMethods=allMethods;
  }
  return allMethods;
}
