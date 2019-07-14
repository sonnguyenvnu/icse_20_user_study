/** 
 * @return True if the super class of this generated model is also extended from a generatedmodel.
 */
public boolean isSuperClassAlsoGenerated(){
  return isSubtypeOfType(superClassElement.asType(),"com.airbnb.epoxy.GeneratedModel<?>");
}
