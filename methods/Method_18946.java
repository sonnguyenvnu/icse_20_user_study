private static boolean definesStateValue(SpecModel specModel,String name,TypeName type){
  for (  StateParamModel stateValue : specModel.getStateValues()) {
    if (stateValue.getName().equals(name) && stateValue.getTypeName().box().equals(type.box())) {
      return true;
    }
  }
  return false;
}
