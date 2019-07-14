public static String applyNewChangeSetSourceToString(@ApplyNewChangeSet int source){
switch (source) {
case ApplyNewChangeSet.NONE:
    return "none";
case ApplyNewChangeSet.SET_ROOT:
  return "setRoot";
case ApplyNewChangeSet.SET_ROOT_ASYNC:
return "setRootAsync";
case ApplyNewChangeSet.UPDATE_STATE:
return "updateState";
case ApplyNewChangeSet.UPDATE_STATE_ASYNC:
return "updateStateAsync";
default :
throw new IllegalStateException("Unknown source");
}
}
