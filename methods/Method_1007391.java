/** 
 * Reloads a class.
 * @param className the fully-qualified class name.
 * @param classFile the contents of the class file.
 */
public void reload(String className,byte[] classFile){
  ReferenceType classtype=toRefType(className);
  Map<ReferenceType,byte[]> map=new HashMap<ReferenceType,byte[]>();
  map.put(classtype,classFile);
  reload2(map,className);
}
