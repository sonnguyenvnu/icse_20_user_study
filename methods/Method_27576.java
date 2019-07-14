@OnClick(R.id.close) public void onCloseClicked(){
  if (!close.isSelected()) {
    open.setSelected(false);
    close.setSelected(true);
    String text=InputHelper.toString(searchEditText);
    if (!InputHelper.isEmpty(text)) {
      text=text.replace("is:open","is:closed");
      searchEditText.setText(text);
      onSearch();
    }
 else {
      searchEditText.setText(String.format("%s %s ",isOpen ? "is:open" : "is:closed",isIssue ? "is:issue" : "is:pr"));
      onSearch();
    }
  }
}
