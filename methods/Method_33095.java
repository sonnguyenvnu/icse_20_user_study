private double getGap(){
  return (getListView() instanceof JFXListView) ? (((JFXListView<?>)getListView()).isExpanded() ? ((JFXListView<?>)getListView()).currentVerticalGapProperty().get() : 0) : 0;
}
