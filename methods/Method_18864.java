private static String getStringRepresentationOfParamType(OptionalParameterType optionalParameterType){
switch (optionalParameterType) {
case PROP:
    return "@Prop T somePropName";
case DIFF_PROP:
  return "@Prop Diff<T> somePropName";
case TREE_PROP:
return "@TreeProp T someTreePropName";
case STATE:
return "@State T someStateName";
case DIFF_STATE:
return "@State Diff<T> someStateName";
case PARAM:
return "@Param T someParamName";
case INJECT_PROP:
return "@InjectProp T someInjectPropName";
case INTER_STAGE_OUTPUT:
return "Output<T> someOutputName";
case PROP_OUTPUT:
return "Output<T> propName, where a prop with type T and name propName is " + "declared elsewhere in the spec";
case STATE_OUTPUT:
return "Output<T> stateName, where a state param with type T and name stateName is " + "declared elsewhere in the spec";
case STATE_VALUE:
return "StateValue<T> stateName, where a state param with type T and name stateName is " + "declared elsewhere in the spec";
case DIFF:
return "@State Diff<T> stateName or @Prop Diff<T> propName, where stateName/propName is " + "a declared state or prop param declared elsewhere in the spec.";
case CACHED_VALUE:
return "@CachedValue T value, where the cached value has a corresponding " + "@OnCalculateCachedValue method";
}
return "Unexpected parameter type - please report to the Components team";
}
