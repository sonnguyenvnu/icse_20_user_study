public boolean isCheckmarkSequenceReversed(){
  if (shouldReverseCheckmarks == null)   shouldReverseCheckmarks=storage.getBoolean("pref_checkmark_reverse_order",false);
  return shouldReverseCheckmarks;
}
