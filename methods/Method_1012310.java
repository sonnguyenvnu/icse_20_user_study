private void init(){
  setClickable(true);
  if (Build.VERSION.SDK_INT < 16) {
    setBackgroundDrawable(useSelector ? getSelector() : getDrawable(0));
  }
 else {
    setBackground(useSelector ? getSelector() : getDrawable(0));
  }
  setSGravity();
}
