public void setChecked(boolean checked,boolean anim){
  isChecked=checked;
  if (checked) {
    setSrcColor(btnFillColor);
    isChecked=true;
    if (anim) {
      showAnim();
    }
  }
 else {
    setSrcColor(btnColor);
    isChecked=false;
    if (anim) {
      setCancel();
    }
  }
  onListenerUpdate(checked);
}
