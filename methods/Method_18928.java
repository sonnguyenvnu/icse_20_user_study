private static ImmutableList<InjectPropModel> getRawInjectProps(ImmutableList<SpecMethodModel<DelegateMethod,Void>> delegateMethods,ImmutableList<SpecMethodModel<EventMethod,EventDeclarationModel>> eventMethods,ImmutableList<SpecMethodModel<EventMethod,EventDeclarationModel>> triggerMethods,@Nullable SpecMethodModel<EventMethod,Void> workingRangeRegisterMethod,ImmutableList<WorkingRangeMethodModel> workingRangeMethods,ImmutableList<SpecMethodModel<UpdateStateMethod,Void>> updateStateMethods){
  final List<InjectPropModel> props=new ArrayList<>();
  for (  SpecMethodModel<DelegateMethod,Void> delegateMethod : delegateMethods) {
    for (    MethodParamModel param : delegateMethod.methodParams) {
      if (param instanceof InjectPropModel) {
        props.add((InjectPropModel)param);
      }
    }
  }
  for (  SpecMethodModel<EventMethod,EventDeclarationModel> eventMethod : eventMethods) {
    for (    MethodParamModel param : eventMethod.methodParams) {
      if (param instanceof InjectPropModel) {
        props.add((InjectPropModel)param);
      }
    }
  }
  for (  SpecMethodModel<EventMethod,EventDeclarationModel> triggerMethod : triggerMethods) {
    for (    MethodParamModel param : triggerMethod.methodParams) {
      if (param instanceof InjectPropModel) {
        props.add((InjectPropModel)param);
      }
    }
  }
  if (workingRangeRegisterMethod != null) {
    for (    MethodParamModel param : workingRangeRegisterMethod.methodParams) {
      if (param instanceof InjectPropModel) {
        props.add((InjectPropModel)param);
      }
    }
  }
  for (  WorkingRangeMethodModel workingRangeMethod : workingRangeMethods) {
    if (workingRangeMethod.enteredRangeModel != null) {
      for (      MethodParamModel param : workingRangeMethod.enteredRangeModel.methodParams) {
        if (param instanceof InjectPropModel) {
          props.add((InjectPropModel)param);
        }
      }
    }
    if (workingRangeMethod.exitedRangeModel != null) {
      for (      MethodParamModel param : workingRangeMethod.exitedRangeModel.methodParams) {
        if (param instanceof InjectPropModel) {
          props.add((InjectPropModel)param);
        }
      }
    }
  }
  for (  SpecMethodModel<UpdateStateMethod,Void> updateStateMethod : updateStateMethods) {
    for (    MethodParamModel param : updateStateMethod.methodParams) {
      if (param instanceof InjectPropModel) {
        props.add((InjectPropModel)param);
      }
    }
  }
  return ImmutableList.copyOf(props);
}
