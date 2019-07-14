/** 
 * @return true if this prop has a default specified in the given set of defaults, falseotherwise.
 */
public boolean hasDefault(ImmutableList<PropDefaultModel> propDefaults){
  for (  PropDefaultModel propDefault : propDefaults) {
    if (propDefault.mType.equals(mParamModel.getTypeName()) && propDefault.mName.equals(mParamModel.getName())) {
      return true;
    }
  }
  return false;
}
