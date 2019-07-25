protected List<String> lines(final String text){
  if (text == null) {
    return emptyList();
  }
  final String[] words=text.trim().split("\\s+");
  final List<String> lines=new ArrayList<>();
  final StringBuilder line=new StringBuilder();
  for (  final String word : words) {
    if (line.length() == 0) {
      line.append(word);
    }
 else     if (line.length() + word.length() + 1 < 30) {
      line.append(' ').append(word);
    }
 else {
      lines.add(line.toString());
      line.setLength(0);
      line.append(word);
    }
  }
  if (line.length() > 0) {
    lines.add(line.toString());
  }
  return lines;
}
