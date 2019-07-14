private void checkPressColor(Canvas canvas){
  if (pressBgColor != Color.TRANSPARENT || pressTextColor != -99) {
    if (pressAdjuster == null) {
      pressAdjuster=new PressAdjuster(pressBgColor).setPressTextColor(pressTextColor);
      addSysAdjuster(pressAdjuster);
    }
    ((PressAdjuster)pressAdjuster).setPressTextColor(pressTextColor);
    ((PressAdjuster)pressAdjuster).setPressBgColor(pressBgColor);
  }
}
