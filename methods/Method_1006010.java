@Override public void dispose(){
  getCompletionTarget().textProperty().removeListener(textChangeListener);
  getCompletionTarget().focusedProperty().removeListener(focusChangedListener);
}
