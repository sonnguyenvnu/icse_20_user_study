@OnUpdateState protected static void onUpdateMeasure(@Param int measureVer,StateValue<Integer> measureVersion){
  measureVersion.set(measureVer);
}
