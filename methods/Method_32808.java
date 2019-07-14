/** 
 * Reload the view given parameters in termux.properties
 * @param buttons matrix of String as defined in termux.properties extrakeysCan Contain The Strings CTRL ALT TAB FN ENTER LEFT RIGHT UP DOWN or normal strings Some aliases are possible like RETURN for ENTER, LT for LEFT and more (@see controlCharsAliases for the whole list). Any string of length > 1 in total Uppercase will print a warning Examples: "ENTER" will trigger the ENTER keycode "LEFT" will trigger the LEFT keycode and be displayed as "?" "?" will input a "?" character "?" will input a "?" character "-_-" will input the string "-_-"
 */
void reload(String[][] buttons,CharDisplayMap charDisplayMap){
  for (  SpecialButtonState state : specialButtons.values())   state.button=null;
  removeAllViews();
  replaceAliases(buttons);
  final int rows=buttons.length;
  final int cols=maximumLength(buttons);
  setRowCount(rows);
  setColumnCount(cols);
  for (int row=0; row < rows; row++) {
    for (int col=0; col < buttons[row].length; col++) {
      final String buttonText=buttons[row][col];
      Button button;
      if (Arrays.asList("CTRL","ALT","FN").contains(buttonText)) {
        SpecialButtonState state=specialButtons.get(SpecialButton.valueOf(buttonText));
        state.isOn=true;
        button=state.button=new ToggleButton(getContext(),null,android.R.attr.buttonBarButtonStyle);
        button.setClickable(true);
      }
 else {
        button=new Button(getContext(),null,android.R.attr.buttonBarButtonStyle);
      }
      final String displayedText=charDisplayMap.get(buttonText,buttonText);
      button.setText(displayedText);
      button.setTextColor(TEXT_COLOR);
      button.setPadding(0,0,0,0);
      final Button finalButton=button;
      button.setOnClickListener(v -> {
        finalButton.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
        View root=getRootView();
        if (Arrays.asList("CTRL","ALT","FN").contains(buttonText)) {
          ToggleButton self=(ToggleButton)finalButton;
          self.setChecked(self.isChecked());
          self.setTextColor(self.isChecked() ? INTERESTING_COLOR : TEXT_COLOR);
        }
 else {
          sendKey(root,buttonText);
        }
      }
);
      button.setOnTouchListener((v,event) -> {
        final View root=getRootView();
switch (event.getAction()) {
case MotionEvent.ACTION_DOWN:
          longPressCount=0;
        v.setBackgroundColor(BUTTON_PRESSED_COLOR);
      if (Arrays.asList("UP","DOWN","LEFT","RIGHT").contains(buttonText)) {
        scheduledExecutor=Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleWithFixedDelay(() -> {
          longPressCount++;
          sendKey(root,buttonText);
        }
,400,80,TimeUnit.MILLISECONDS);
      }
    return true;
case MotionEvent.ACTION_MOVE:
  if (Arrays.asList("/","-").contains(buttonText)) {
    if (popupWindow == null && event.getY() < 0) {
      v.setBackgroundColor(BUTTON_COLOR);
      String text="-".equals(buttonText) ? "|" : "\\";
      popup(v,text);
    }
    if (popupWindow != null && event.getY() > 0) {
      v.setBackgroundColor(BUTTON_PRESSED_COLOR);
      popupWindow.dismiss();
      popupWindow=null;
    }
  }
return true;
case MotionEvent.ACTION_UP:
case MotionEvent.ACTION_CANCEL:
v.setBackgroundColor(BUTTON_COLOR);
if (scheduledExecutor != null) {
scheduledExecutor.shutdownNow();
scheduledExecutor=null;
}
if (longPressCount == 0) {
if (popupWindow != null && Arrays.asList("/","-").contains(buttonText)) {
popupWindow.setContentView(null);
popupWindow.dismiss();
popupWindow=null;
sendKey(root,"-".equals(buttonText) ? "|" : "\\");
}
 else {
v.performClick();
}
}
return true;
default :
return true;
}
}
);
LayoutParams param=new GridLayout.LayoutParams();
param.width=0;
if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
param.height=(int)(37.5 * getResources().getDisplayMetrics().density + 0.5);
}
 else {
param.height=0;
}
param.setMargins(0,0,0,0);
param.columnSpec=GridLayout.spec(col,GridLayout.FILL,1.f);
param.rowSpec=GridLayout.spec(row,GridLayout.FILL,1.f);
button.setLayoutParams(param);
addView(button);
}
}
}
