private void appendMilestone(MilestoneModel item){
  if (popupWindow != null) {
    popupWindow.dismiss();
  }
  appendIfEmpty();
  String text=InputHelper.toString(searchEditText);
  String regex="milestone:(\".+\"|\\S+)";
  if (item.getTitle().equalsIgnoreCase(getString(R.string.clear))) {
    text=text.replaceAll(regex,"");
    searchEditText.setText(text);
    onSearch();
    return;
  }
  if (!text.replaceAll(regex,"milestone:\"" + item.getTitle() + "\"").equalsIgnoreCase(text)) {
    String space=text.endsWith(" ") ? "" : " ";
    text=text.replaceAll(regex,space + "milestone:\"" + item.getTitle() + "\"");
  }
 else {
    text+=text.endsWith(" ") ? "" : " ";
    text+="milestone:\"" + item.getTitle() + "\"";
  }
  searchEditText.setText(text);
  onSearch();
}
