private void updatePickers(){
  Iterator<OnDateChangedListener> iterator=mListeners.iterator();
  while (iterator.hasNext()) {
    iterator.next().onDateChanged();
  }
}
