public String render(Diff diff){
  List<DiffLine<?>> lines=diff.getLines();
  String expected=Joiner.on("\n").join(from(lines).transform(EXPECTED));
  String actual=Joiner.on("\n").join(from(lines).transform(ACTUAL));
  return lines.isEmpty() ? "" : junitStyleDiffMessage(expected,actual);
}
