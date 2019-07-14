private void checkShareButton(){
  if (shareCell == null || doneItem == null || inputFields[FIELD_IP] == null || inputFields[FIELD_PORT] == null) {
    return;
  }
  if (inputFields[FIELD_IP].length() != 0 && Utilities.parseInt(inputFields[FIELD_PORT].getText().toString()) != 0) {
    shareCell.getTextView().setAlpha(1.0f);
    doneItem.setAlpha(1.0f);
    shareCell.setEnabled(true);
    doneItem.setEnabled(true);
  }
 else {
    shareCell.getTextView().setAlpha(0.5f);
    doneItem.setAlpha(0.5f);
    shareCell.setEnabled(false);
    doneItem.setEnabled(false);
  }
}
