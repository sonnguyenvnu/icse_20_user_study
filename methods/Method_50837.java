public Map<String,TimedResult> getLabeledMeasurements(final TimedOperationCategory category){
  final Map<String,TimedResult> ret=new HashMap<>();
  for (  final Map.Entry<TimedOperationKey,TimedResult> entry : results.entrySet()) {
    final TimedOperationKey timedOperation=entry.getKey();
    if (timedOperation.category == category && timedOperation.label != null) {
      ret.put(timedOperation.label,entry.getValue());
    }
  }
  return ret;
}
