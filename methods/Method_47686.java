private int getQuickSelectPosition(@NonNull Frequency freq){
  if (freq.equals(Frequency.DAILY))   return 0;
  if (freq.equals(Frequency.WEEKLY))   return 1;
  if (freq.equals(Frequency.TWO_TIMES_PER_WEEK))   return 2;
  if (freq.equals(Frequency.FIVE_TIMES_PER_WEEK))   return 3;
  return -1;
}
