private void invalidateSelectedDates(){
  validateSelectedDates();
  for (  V pagerView : currentViews) {
    pagerView.setSelectedDates(selectedDates);
  }
}
