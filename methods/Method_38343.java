/** 
 * Saves object reference.
 */
public void setObjectReference(final String name,final Object object){
  if (objectRefs == null) {
    objectRefs=new HashMap<>();
  }
  objectRefs.put(name,object);
}
