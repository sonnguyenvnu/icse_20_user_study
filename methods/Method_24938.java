public void updateValue(){
  float change=getChange();
  if ("int".equals(type)) {
    if (newValue.intValue() + (int)change > Integer.MAX_VALUE || newValue.intValue() + (int)change < Integer.MIN_VALUE) {
      change=0;
      return;
    }
    setValue(newValue.intValue() + (int)change);
  }
 else   if ("hex".equals(type)) {
    setValue(newValue.intValue() + (int)change);
  }
 else   if ("webcolor".equals(type)) {
    setValue(newValue.intValue() + (int)change);
  }
 else   if ("float".equals(type)) {
    setValue(newValue.floatValue() + change);
  }
  updateColorBox();
}
