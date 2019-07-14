private void updatePasswordFields(){
  if (currentStep != 6 || bottomCell[2] == null) {
    return;
  }
  doneItem.setVisibility(View.VISIBLE);
  if (currentPassword == null) {
    showEditDoneProgress(true,true);
    bottomCell[2].setVisibility(View.GONE);
    settingsCell[0].setVisibility(View.GONE);
    settingsCell[1].setVisibility(View.GONE);
    codeFieldCell.setVisibility(View.GONE);
    headerCell[0].setVisibility(View.GONE);
    headerCell[1].setVisibility(View.GONE);
    bottomCell[0].setVisibility(View.GONE);
    for (int a=0; a < FIELDS_COUNT_PASSWORD; a++) {
      ((View)inputFields[a].getParent()).setVisibility(View.GONE);
    }
    for (int a=0; a < dividers.size(); a++) {
      dividers.get(a).setVisibility(View.GONE);
    }
  }
 else {
    showEditDoneProgress(true,false);
    if (waitingForEmail) {
      bottomCell[2].setText(LocaleController.formatString("EmailPasswordConfirmText2",R.string.EmailPasswordConfirmText2,currentPassword.email_unconfirmed_pattern != null ? currentPassword.email_unconfirmed_pattern : ""));
      bottomCell[2].setVisibility(View.VISIBLE);
      settingsCell[0].setVisibility(View.VISIBLE);
      settingsCell[1].setVisibility(View.VISIBLE);
      codeFieldCell.setVisibility(View.VISIBLE);
      bottomCell[1].setText("");
      headerCell[0].setVisibility(View.GONE);
      headerCell[1].setVisibility(View.GONE);
      bottomCell[0].setVisibility(View.GONE);
      for (int a=0; a < FIELDS_COUNT_PASSWORD; a++) {
        ((View)inputFields[a].getParent()).setVisibility(View.GONE);
      }
      for (int a=0; a < dividers.size(); a++) {
        dividers.get(a).setVisibility(View.GONE);
      }
    }
 else {
      bottomCell[2].setVisibility(View.GONE);
      settingsCell[0].setVisibility(View.GONE);
      settingsCell[1].setVisibility(View.GONE);
      bottomCell[1].setText(LocaleController.getString("PaymentPasswordEmailInfo",R.string.PaymentPasswordEmailInfo));
      codeFieldCell.setVisibility(View.GONE);
      headerCell[0].setVisibility(View.VISIBLE);
      headerCell[1].setVisibility(View.VISIBLE);
      bottomCell[0].setVisibility(View.VISIBLE);
      for (int a=0; a < FIELDS_COUNT_PASSWORD; a++) {
        ((View)inputFields[a].getParent()).setVisibility(View.VISIBLE);
      }
      for (int a=0; a < dividers.size(); a++) {
        dividers.get(a).setVisibility(View.VISIBLE);
      }
    }
  }
}
