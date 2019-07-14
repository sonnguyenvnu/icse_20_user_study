public void setDateTextAppearance(int taId){
  for (  DayView dayView : dayViews) {
    dayView.setTextAppearance(getContext(),taId);
  }
}
