public void setCheckmarkSequenceReversed(boolean reverse){
  shouldReverseCheckmarks=reverse;
  storage.putBoolean("pref_checkmark_reverse_order",reverse);
  for (  Listener l : listeners)   l.onCheckmarkSequenceChanged();
}
