public void closeTextEnter(boolean apply){
  if (!editingText || !(currentEntityView instanceof TextPaintView)) {
    return;
  }
  TextPaintView textPaintView=(TextPaintView)currentEntityView;
  toolsView.setVisibility(VISIBLE);
  AndroidUtilities.hideKeyboard(textPaintView.getFocusedView());
  textPaintView.getFocusedView().clearFocus();
  textPaintView.endEditing();
  if (!apply) {
    textPaintView.setText(initialText);
  }
  if (textPaintView.getText().trim().length() == 0) {
    entitiesView.removeView(textPaintView);
    selectEntity(null);
  }
 else {
    textPaintView.setPosition(editedTextPosition);
    textPaintView.setRotation(editedTextRotation);
    textPaintView.setScale(editedTextScale);
    editedTextPosition=null;
    editedTextRotation=0.0f;
    editedTextScale=0.0f;
  }
  setTextDimVisibility(false,textPaintView);
  editingText=false;
  initialText=null;
  curtainView.setVisibility(View.GONE);
}
