@OnEvent(ReMeasureEvent.class) protected static void onRemeasure(ComponentContext c,@State int measureVersion){
  Recycler.onUpdateMeasureAsync(c,measureVersion + 1);
}
