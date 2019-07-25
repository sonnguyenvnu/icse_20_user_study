/** 
 * Initializes this text area.
 */
protected void init(){
  setRTextAreaUI(createRTextAreaUI());
  enableEvents(AWTEvent.COMPONENT_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
  setHighlightCurrentLine(true);
  setCurrentLineHighlightColor(getDefaultCurrentLineHighlightColor());
  setMarginLineEnabled(false);
  setMarginLineColor(getDefaultMarginLineColor());
  setMarginLinePosition(getDefaultMarginLinePosition());
  setBackgroundObject(Color.WHITE);
  setWrapStyleWord(true);
  setTabSize(5);
  setForeground(Color.BLACK);
  setTabsEmulated(false);
  previousCaretY=currentCaretY=getInsets().top;
  mouseListener=createMouseListener();
  addFocusListener(mouseListener);
  addCurrentLineHighlightListeners();
}
