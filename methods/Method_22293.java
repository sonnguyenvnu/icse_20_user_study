/** 
 * Creates a main view containing text of resText, or nothing if not found
 * @return the main view
 */
@NonNull protected View getMainView(){
  final TextView text=new TextView(this);
  final String dialogText=dialogConfiguration.text();
  if (dialogText != null) {
    text.setText(dialogText);
  }
  return text;
}
