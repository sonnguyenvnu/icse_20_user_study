@OnClick(R.id.open) public void onOpenClicked(){
  if (!open.isSelected()) {
    open.setSelected(true);
    close.setSelected(false);
    String text=InputHelper.toString(searchEditText);
    if (!InputHelper.isEmpty(text)) {
      text=text.replace("is:closed","is:open");
      searchEditText.setText(text);
      onSearch();
    }
 else {
      searchEditText.setText(String.format("%s %s ",isOpen ? "is:open" : "is:closed",isIssue ? "is:issue" : "is:pr"));
      if (!InputHelper.isEmpty(criteria)) {
        searchEditText.setText(String.format("%s%s",InputHelper.toString(searchEditText),criteria));
        criteria=null;
      }
      onSearch();
    }
  }
}
