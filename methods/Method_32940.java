/** 
 * Attach a  {@link TerminalSession} to this view.
 * @param session The {@link TerminalSession} this view will be displaying.
 */
public boolean attachSession(TerminalSession session){
  if (session == mTermSession)   return false;
  mTopRow=0;
  mTermSession=session;
  mEmulator=null;
  mCombiningAccent=0;
  updateSize();
  setVerticalScrollBarEnabled(true);
  return true;
}
