void popup(View view,String text){
  int width=view.getMeasuredWidth();
  int height=view.getMeasuredHeight();
  Button button=new Button(getContext(),null,android.R.attr.buttonBarButtonStyle);
  button.setText(text);
  button.setTextColor(TEXT_COLOR);
  button.setPadding(0,0,0,0);
  button.setMinHeight(0);
  button.setMinWidth(0);
  button.setMinimumWidth(0);
  button.setMinimumHeight(0);
  button.setWidth(width);
  button.setHeight(height);
  button.setBackgroundColor(BUTTON_PRESSED_COLOR);
  popupWindow=new PopupWindow(this);
  popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
  popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
  popupWindow.setContentView(button);
  popupWindow.setOutsideTouchable(true);
  popupWindow.setFocusable(false);
  popupWindow.showAsDropDown(view,0,-2 * height);
}
