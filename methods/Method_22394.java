/** 
 * Tools require an 'Editor' object when they're instantiated, but the activeEditor will be null when the first Editor that opens is creating its Tools menu. This will temporarily set the activeEditor to the one that's opening so that we don't go all NPE on startup. If there's already an active editor, then this does nothing.
 */
public void checkFirstEditor(Editor editor){
  if (activeEditor == null) {
    activeEditor=editor;
  }
}
