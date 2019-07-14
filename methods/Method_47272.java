/** 
 * @return ReturnState.state
 */
private int doValidate(boolean onlySetWarning){
  ReturnState state=mValidator.isTextValid(mEditText.getText().toString());
switch (state.state) {
case ReturnState.STATE_NORMAL:
    mTextInputLayout.removeError();
  setEditTextIcon(null);
mButton.setEnabled(true);
break;
case ReturnState.STATE_ERROR:
if (!onlySetWarning) {
mTextInputLayout.setError(mContext.getString(state.text));
setEditTextIcon(errorDrawable);
}
mButton.setEnabled(false);
break;
case ReturnState.STATE_WARNING:
mTextInputLayout.setWarning(state.text);
setEditTextIcon(warningDrawable);
mButton.setEnabled(true);
break;
}
return state.state;
}
