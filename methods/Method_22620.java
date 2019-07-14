/** 
 * Set the default L & F. While I enjoy the bounty of the sixteen possible exception types that this UIManager method might throw, I feel that in just this one particular case, I'm being spoiled by those engineers at Sun, those Masters of the Abstractionverse. So instead, I'll pretend that I'm not offered eleven dozen ways to report to the user exactly what went wrong, and I'll bundle them all into a single catch-all "Exception". Because in the end, all I really care about is whether things worked or not. And even then, I don't care.
 * @throws Exception Just like I said.
 */
public void setLookAndFeel() throws Exception {
  String laf=Preferences.get("editor.laf");
  if (laf == null || laf.length() == 0) {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  }
 else {
    UIManager.setLookAndFeel(laf);
  }
}
