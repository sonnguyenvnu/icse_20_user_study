public TimedResult getUnlabeledMeasurements(final TimedOperationCategory category){
  for (  final Map.Entry<TimedOperationKey,TimedResult> entry : results.entrySet()) {
    final TimedOperationKey timedOperation=entry.getKey();
    if (timedOperation.category == category && timedOperation.label == null) {
      return entry.getValue();
    }
  }
  return null;
}
