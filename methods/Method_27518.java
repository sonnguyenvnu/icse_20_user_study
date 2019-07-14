private boolean canExit(){
  if (backPressTimer + 2000 > System.currentTimeMillis()) {
    return true;
  }
 else {
    showMessage(R.string.press_again_to_exit,R.string.press_again_to_exit);
  }
  backPressTimer=System.currentTimeMillis();
  return false;
}
