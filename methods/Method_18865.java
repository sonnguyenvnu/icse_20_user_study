/** 
 * Compare a given  {@link PropModel} to the underlying propmodel of this instance. If a cachedname is provided, it will override the check with the underlying model.
 */
public boolean isSameUnderlyingPropModel(PropModel propModel,@Nullable String cachedName){
  return (propModel.getName().equals(getName()) || propModel.getName().equals(cachedName)) && propModel.getTypeName().box().equals(mUnderlyingPropModel.getTypeName().box()) && propModel.isOptional() == isOptional() && propModel.getResType() == getResType() && propModel.getVarArgsSingleName().equals(getVarArgsSingleName());
}
