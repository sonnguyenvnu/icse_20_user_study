private List<Integer> overLengthLineIndicesIn(Comment comment){
  int maxLength=getProperty(MAX_LINE_LENGTH);
  List<Integer> indicies=new ArrayList<>();
  String[] lines=comment.getImage().split(CR);
  int offset=comment.getBeginLine();
  for (int i=0; i < lines.length; i++) {
    String cleaned=withoutCommentMarkup(lines[i]);
    if (cleaned.length() > maxLength) {
      indicies.add(i + offset);
    }
  }
  return indicies;
}
