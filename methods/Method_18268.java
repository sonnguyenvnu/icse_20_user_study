private static String sourceToString(@CalculateLayoutSource int source){
switch (source) {
case CalculateLayoutSource.SET_ROOT_SYNC:
    return "setRoot";
case CalculateLayoutSource.SET_SIZE_SPEC_SYNC:
  return "setSizeSpec";
case CalculateLayoutSource.UPDATE_STATE_SYNC:
return "updateStateSync";
case CalculateLayoutSource.SET_ROOT_ASYNC:
return "setRootAsync";
case CalculateLayoutSource.SET_SIZE_SPEC_ASYNC:
return "setSizeSpecAsync";
case CalculateLayoutSource.UPDATE_STATE_ASYNC:
return "updateStateAsync";
case CalculateLayoutSource.MEASURE:
return "measure";
case CalculateLayoutSource.TEST:
return "test";
case CalculateLayoutSource.NONE:
return "none";
default :
throw new RuntimeException("Unknown calculate layout source: " + source);
}
}
