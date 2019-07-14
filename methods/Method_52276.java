private List<String> illegalTermsIn(Comment comment){
  if (currentBadWords.isEmpty()) {
    return Collections.emptyList();
  }
  String commentText=filteredCommentIn(comment);
  if (StringUtils.isBlank(commentText)) {
    return Collections.emptyList();
  }
  if (!caseSensitive) {
    commentText=commentText.toUpperCase(Locale.ROOT);
  }
  List<String> foundWords=new ArrayList<>();
  for (int i=0; i < currentBadWords.size(); i++) {
    if (commentText.contains(currentBadWords.get(i))) {
      foundWords.add(originalBadWords.get(i));
    }
  }
  return foundWords;
}
