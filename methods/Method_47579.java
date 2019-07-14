@Override public CharSequence getContentDescription(){
  CharSequence itemText=getText();
  if (mDrawCircle) {
    return String.format(mItemIsSelectedText,itemText);
  }
 else {
    return itemText;
  }
}
