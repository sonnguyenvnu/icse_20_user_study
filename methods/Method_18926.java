/** 
 * Extract props without taking deduplication and name caching into account. 
 */
private static ImmutableList<PropModel> getRawProps(ImmutableList<SpecMethodModel<DelegateMethod,Void>> delegateMethods,ImmutableList<SpecMethodModel<EventMethod,EventDeclarationModel>> eventMethods,ImmutableList<SpecMethodModel<EventMethod,EventDeclarationModel>> triggerMethods,@Nullable SpecMethodModel<EventMethod,Void> workingRangeRegisterMethod,ImmutableList<WorkingRangeMethodModel> workingRangeMethods,ImmutableList<SpecMethodModel<UpdateStateMethod,Void>> updateStateMethods,ImmutableList<SpecMethodModel<BindDynamicValueMethod,Void>> bindDynamicValueMethods){
  final List<PropModel> props=new ArrayList<>();
  for (  SpecMethodModel<DelegateMethod,Void> delegateMethod : delegateMethods) {
    for (    MethodParamModel param : delegateMethod.methodParams) {
      if (param instanceof PropModel) {
        props.add((PropModel)param);
      }
    }
  }
  for (  SpecMethodModel<EventMethod,EventDeclarationModel> eventMethod : eventMethods) {
    for (    MethodParamModel param : eventMethod.methodParams) {
      if (param instanceof PropModel) {
        props.add((PropModel)param);
      }
    }
  }
  for (  SpecMethodModel<EventMethod,EventDeclarationModel> triggerMethod : triggerMethods) {
    for (    MethodParamModel param : triggerMethod.methodParams) {
      if (param instanceof PropModel) {
        props.add((PropModel)param);
      }
    }
  }
  if (workingRangeRegisterMethod != null) {
    for (    MethodParamModel param : workingRangeRegisterMethod.methodParams) {
      if (param instanceof PropModel) {
        props.add((PropModel)param);
      }
    }
  }
  for (  WorkingRangeMethodModel workingRangeMethod : workingRangeMethods) {
    if (workingRangeMethod.enteredRangeModel != null) {
      for (      MethodParamModel param : workingRangeMethod.enteredRangeModel.methodParams) {
        if (param instanceof PropModel) {
          props.add((PropModel)param);
        }
      }
    }
    if (workingRangeMethod.exitedRangeModel != null) {
      for (      MethodParamModel param : workingRangeMethod.exitedRangeModel.methodParams) {
        if (param instanceof PropModel) {
          props.add((PropModel)param);
        }
      }
    }
  }
  for (  SpecMethodModel<UpdateStateMethod,Void> updateStateMethod : updateStateMethods) {
    for (    MethodParamModel param : updateStateMethod.methodParams) {
      if (param instanceof PropModel) {
        props.add((PropModel)param);
      }
    }
  }
  for (  SpecMethodModel<DelegateMethod,Void> delegateMethod : delegateMethods) {
    for (    MethodParamModel param : delegateMethod.methodParams) {
      if (param instanceof DiffPropModel) {
        props.add(((DiffPropModel)param).getUnderlyingPropModel());
      }
    }
  }
  for (  SpecMethodModel<BindDynamicValueMethod,Void> bindDynamicValueMethod : bindDynamicValueMethods) {
    for (    MethodParamModel param : bindDynamicValueMethod.methodParams) {
      if (param instanceof PropModel) {
        props.add((PropModel)param);
      }
    }
  }
  return ImmutableList.copyOf(props);
}
