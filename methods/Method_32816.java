/** 
 * Try switching to session and note about it, but do nothing if already displaying the session. 
 */
void switchToSession(TerminalSession session){
  if (mTerminalView.attachSession(session)) {
    noteSessionInfo();
    updateBackgroundColor();
  }
}
