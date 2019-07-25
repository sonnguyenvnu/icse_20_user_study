public void show(@NonNull final EditText imeTarget,@NonNull final InputView input){
  if (isKeyboardOpen()) {
    hideSoftkey(imeTarget,new Runnable(){
      @Override public void run(){
        hideAttachedInput(true);
        input.show(getKeyboardHeight(),true);
        current=input;
      }
    }
);
  }
 else {
    if (current != null)     current.hide(true);
    input.show(getKeyboardHeight(),current != null);
    current=input;
  }
}
