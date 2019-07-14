private void doTranslation(final TextView title,final int translate){
  if (orientation == MaterialCalendarView.HORIZONTAL) {
    title.setTranslationX(translate);
  }
 else {
    title.setTranslationY(translate);
  }
}
