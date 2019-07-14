private int getSelectedPos(String value){
  if (mDisplayedValues == null) {
    try {
      return Integer.parseInt(value);
    }
 catch (    NumberFormatException e) {
    }
  }
 else {
    for (int i=0; i < mDisplayedValues.length; i++) {
      value=value.toLowerCase();
      if (mDisplayedValues[i].toLowerCase().startsWith(value)) {
        return mMinValue + i;
      }
    }
    try {
      return Integer.parseInt(value);
    }
 catch (    NumberFormatException e) {
    }
  }
  return mMinValue;
}
