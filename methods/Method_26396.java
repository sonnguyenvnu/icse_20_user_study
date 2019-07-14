/** 
 * Returns true if the type is an annotation. 
 */
public static boolean isAnnotation(VisitorState state,Type type){
  return isAssignableTo(type,Suppliers.ANNOTATION_TYPE,state);
}
