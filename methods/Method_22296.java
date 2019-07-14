/** 
 * creates a email label view with resEmailPrompt as text
 * @return the label or null if there is no resource
 */
@Nullable protected View getEmailLabel(){
  final String emailPrompt=dialogConfiguration.emailPrompt();
  if (emailPrompt != null) {
    final TextView labelView=new TextView(this);
    labelView.setText(emailPrompt);
    return labelView;
  }
  return null;
}
