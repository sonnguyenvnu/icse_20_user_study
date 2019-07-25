/** 
 * Generate the next Duration based on the supplied function. If you've specified a start duration explicitly then this start duration will override the value of <code>previousDuration</code> when <code>pollCount</code> is 1 (i.e. the poll delay).
 * @param pollCount        The number of times the condition has been polled (evaluated). Always a positive integer.
 * @param previousDuration The duration of the previously returned poll interval.
 * @return The duration of the next poll interval
 */
public Duration next(int pollCount,Duration previousDuration){
  final Duration durationToUse;
  if (pollCount == 1 && startDuration != null) {
    durationToUse=startDuration;
  }
 else {
    durationToUse=previousDuration;
  }
  return function.apply(durationToUse);
}
