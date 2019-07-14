/** 
 * Get the bounded value for a specific range. If the value is outside the max, you can't edit right away, so just act as if it's already been bounded and return the bounded value, then fire an event to set it to the value that was just returned.
 */
protected int bounded(int current,final JTextField field,final int max){
  String text=field.getText();
  if (text.length() == 0) {
    return 0;
  }
  try {
    int value=Integer.parseInt(text);
    if (value > max) {
      SwingUtilities.invokeLater(new Runnable(){
        public void run(){
          field.setText(String.valueOf(max));
        }
      }
);
      return max;
    }
    return value;
  }
 catch (  NumberFormatException e) {
    return current;
  }
}
