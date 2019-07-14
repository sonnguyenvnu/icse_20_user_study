/** 
 * Call this to submit the INPUT tag.
 */
public void submitTag(){
  final TagView inputTag=getInputTag();
  if (inputTag != null && inputTag.isInputAvailable()) {
    inputTag.endInput();
    if (mOnTagChangeListener != null) {
      mOnTagChangeListener.onAppend(TagGroup.this,inputTag.getText().toString());
    }
    appendInputTag();
  }
}
