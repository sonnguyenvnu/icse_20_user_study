private void appendAssignee(User item){
  if (popupWindow != null) {
    popupWindow.dismiss();
  }
  appendIfEmpty();
  String text=InputHelper.toString(searchEditText);
  String regex="assignee:(\".+\"|\\S+)";
  if (item.getLogin().equalsIgnoreCase(getString(R.string.clear))) {
    text=text.replaceAll(regex,"");
    searchEditText.setText(text);
    onSearch();
    return;
  }
  if (!text.replaceAll(regex,"assignee:\"" + item.getLogin() + "\"").equalsIgnoreCase(text)) {
    String space=text.endsWith(" ") ? "" : " ";
    text=text.replaceAll(regex,space + "assignee:\"" + item.getLogin() + "\"");
  }
 else {
    text+=text.endsWith(" ") ? "" : " ";
    text+="assignee:\"" + item.getLogin() + "\"";
  }
  searchEditText.setText(text);
  onSearch();
}
