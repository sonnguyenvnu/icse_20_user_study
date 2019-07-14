/** 
 * For non-daily habits, some groups of repetitions generate many checkmarks. For example, for weekly habits, each repetition generates seven checkmarks. For twice-a-week habits, two repetitions that are close enough together also generate seven checkmarks. <p> This group of generated checkmarks, for a given set of repetition, is represented by an interval. This function computes the list of intervals for a given list of repetitions. It tries to build the intervals as far away in the future as possible.
 */
@NonNull static ArrayList<Interval> buildIntervals(@NonNull Frequency freq,@NonNull Repetition[] reps){
  int num=freq.getNumerator();
  int den=freq.getDenominator();
  ArrayList<Interval> intervals=new ArrayList<>();
  for (int i=0; i < reps.length - num + 1; i++) {
    Repetition first=reps[i];
    Repetition last=reps[i + num - 1];
    long distance=first.getTimestamp().daysUntil(last.getTimestamp());
    if (distance >= den)     continue;
    Timestamp begin=first.getTimestamp();
    Timestamp center=last.getTimestamp();
    Timestamp end=begin.plus(den - 1);
    intervals.add(new Interval(begin,center,end));
  }
  return intervals;
}
