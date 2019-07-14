private void matchStartAndEnd(@NonNull TransitionValuesMaps startValues,@NonNull TransitionValuesMaps endValues){
  ArrayMap<View,TransitionValues> unmatchedStart=new ArrayMap<View,TransitionValues>(startValues.viewValues);
  ArrayMap<View,TransitionValues> unmatchedEnd=new ArrayMap<View,TransitionValues>(endValues.viewValues);
  for (int i=0; i < mMatchOrder.length; i++) {
switch (mMatchOrder[i]) {
case MATCH_INSTANCE:
      matchInstances(unmatchedStart,unmatchedEnd);
    break;
case MATCH_NAME:
  matchNames(unmatchedStart,unmatchedEnd,startValues.nameValues,endValues.nameValues);
break;
case MATCH_ID:
matchIds(unmatchedStart,unmatchedEnd,startValues.idValues,endValues.idValues);
break;
case MATCH_ITEM_ID:
matchItemIds(unmatchedStart,unmatchedEnd,startValues.itemIdValues,endValues.itemIdValues);
break;
}
}
addUnmatched(unmatchedStart,unmatchedEnd);
}
