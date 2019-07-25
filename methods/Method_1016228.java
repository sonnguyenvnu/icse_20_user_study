private WebButton copy(final WebButton button){
  final WebButton copy=new WebButton(){
    @Override public Dimension getPreferredSize(){
      return button.getSize();
    }
  }
;
  copySettings(button,copy);
  copy.setFocusable(true);
  copy.setUndecorated(true);
  copy.setCursor(button.getCursor());
  return copy;
}
