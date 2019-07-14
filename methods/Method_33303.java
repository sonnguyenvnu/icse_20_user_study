@Override protected void handleControlPropertyChanged(String p){
  if ("DEFAULT_COLOR".equals(p)) {
    ((JFXTextField)getEditor()).setFocusColor(jfxDatePicker.getDefaultColor());
  }
 else   if ("DAY_CELL_FACTORY".equals(p)) {
    updateDisplayNode();
    content=null;
    popup=null;
  }
 else   if ("CONVERTER".equals(p)) {
    updateDisplayNode();
  }
 else   if ("EDITOR".equals(p)) {
    getEditableInputNode();
  }
 else   if ("SHOWING".equals(p)) {
    if (jfxDatePicker.isShowing()) {
      if (content != null) {
        LocalDate date=jfxDatePicker.getValue();
        content.displayedYearMonthProperty().set((date != null) ? YearMonth.from(date) : YearMonth.now());
        content.updateValues();
      }
      show();
    }
 else {
      hide();
    }
  }
 else   if ("SHOW_WEEK_NUMBERS".equals(p)) {
    if (content != null) {
      content.updateContentGrid();
      content.updateWeekNumberDateCells();
    }
  }
 else   if ("VALUE".equals(p)) {
    updateDisplayNode();
    if (content != null) {
      LocalDate date=jfxDatePicker.getValue();
      content.displayedYearMonthProperty().set((date != null) ? YearMonth.from(date) : YearMonth.now());
      content.updateValues();
    }
    jfxDatePicker.fireEvent(new ActionEvent());
  }
 else {
    super.handleControlPropertyChanged(p);
  }
}
