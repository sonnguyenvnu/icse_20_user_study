private void appendLabel(LabelModel item){
  if (popupWindow != null) {
    popupWindow.dismiss();
  }
  appendIfEmpty();
  String text=InputHelper.toString(searchEditText);
  String regex="label:(\".+\"|\\S+)";
  if (item.getName().equalsIgnoreCase(getString(R.string.clear))) {
    text=text.replaceAll(regex,"");
    searchEditText.setText(text);
    onSearch();
    return;
  }
  if (!text.replaceAll(regex,"label:\"" + item.getName() + "\"").equalsIgnoreCase(text)) {
    String space=text.endsWith(" ") ? "" : " ";
    text=text.replaceAll(regex,space + "label:\"" + item.getName() + "\"");
  }
 else {
    text+=text.endsWith(" ") ? "" : " ";
    text+="label:\"" + item.getName() + "\"";
  }
  searchEditText.setText(text);
  onSearch();
}
