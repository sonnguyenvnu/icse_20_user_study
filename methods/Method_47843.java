private void computeYesNo(Repetition[] reps){
  ArrayList<Interval> intervals;
  intervals=buildIntervals(habit.getFrequency(),reps);
  snapIntervalsTogether(intervals);
  add(buildCheckmarksFromIntervals(reps,intervals));
}
