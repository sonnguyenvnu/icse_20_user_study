private boolean isDrawSelectionBackground(){
  return isPressed() && isCheckPressed || !isCheckPressed && isPressed || isHighlighted;
}
