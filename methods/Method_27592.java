private void appendSort(String item){
  dismissPopup();
  appendIfEmpty();
  Resources resources=getResources();
  String regex="sort:(\".+\"|\\S+)";
  String oldestQuery="created-asc";
  String mostCommentedQuery="comments-desc";
  String leastCommentedQuery="comments-asc";
  String recentlyUpdatedQuery="updated-desc";
  String leastRecentUpdatedQuery="updated-asc";
  String sortThumbUp="reactions-%2B1-desc";
  String sortThumbDown="reactions--1-desc";
  String sortThumbLaugh="reactions-smile-desc";
  String sortThumbHooray="reactions-tada-desc";
  String sortThumbConfused="reactions-thinking_face-desc";
  String sortThumbHeart="reactions-heart-desc";
  String toQuery="";
  String text=InputHelper.toString(searchEditText);
  if (item.equalsIgnoreCase(resources.getString(R.string.newest))) {
    text=text.replaceAll(regex,"");
    if (!InputHelper.toString(searchEditText).equalsIgnoreCase(text)) {
      searchEditText.setText(text);
      onSearch();
    }
    return;
  }
  if (item.equalsIgnoreCase(resources.getString(R.string.oldest))) {
    toQuery=oldestQuery;
  }
 else   if (item.equalsIgnoreCase(resources.getString(R.string.most_commented))) {
    toQuery=mostCommentedQuery;
  }
 else   if (item.equalsIgnoreCase(resources.getString(R.string.least_commented))) {
    toQuery=leastCommentedQuery;
  }
 else   if (item.equalsIgnoreCase(resources.getString(R.string.recently_updated))) {
    toQuery=recentlyUpdatedQuery;
  }
 else   if (item.equalsIgnoreCase(resources.getString(R.string.least_recent_updated))) {
    toQuery=leastRecentUpdatedQuery;
  }
 else   if (item.equalsIgnoreCase(CommentsHelper.getThumbsUp())) {
    toQuery=sortThumbUp;
  }
 else   if (item.equalsIgnoreCase(CommentsHelper.getThumbsDown())) {
    toQuery=sortThumbDown;
  }
 else   if (item.equalsIgnoreCase(CommentsHelper.getLaugh())) {
    toQuery=sortThumbLaugh;
  }
 else   if (item.equalsIgnoreCase(CommentsHelper.getHooray())) {
    toQuery=sortThumbHooray;
  }
 else   if (item.equalsIgnoreCase(CommentsHelper.getSad())) {
    toQuery=sortThumbConfused;
  }
 else   if (item.equalsIgnoreCase(CommentsHelper.getHeart())) {
    toQuery=sortThumbHeart;
  }
  if (!text.replaceAll(regex,"sort:\"" + toQuery + "\"").equalsIgnoreCase(text)) {
    String space=text.endsWith(" ") ? "" : " ";
    text=text.replaceAll(regex,space + "sort:\"" + toQuery + "\"");
  }
 else {
    text+=text.endsWith(" ") ? "" : " ";
    text+="sort:\"" + toQuery + "\"";
  }
  if (!InputHelper.toString(searchEditText).equalsIgnoreCase(text)) {
    searchEditText.setText(text);
    onSearch();
  }
}
