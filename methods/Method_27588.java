private void appendIfEmpty(){
  if (InputHelper.isEmpty(searchEditText))   if (open.isSelected()) {
    searchEditText.setText(isIssue ? "is:issue is:open " : "is:pr is:open ");
  }
 else   if (close.isSelected()) {
    searchEditText.setText(isIssue ? "is:issue is:close " : "is:pr is:close ");
  }
 else {
    searchEditText.setText(isIssue ? "is:issue is:open " : "is:pr is:open ");
  }
}
