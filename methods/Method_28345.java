@Override public void onRepoPinned(boolean isPinned){
  pinImage.setImageResource(isPinned ? R.drawable.ic_pin_filled : R.drawable.ic_pin);
  pinLayout.setEnabled(true);
}
