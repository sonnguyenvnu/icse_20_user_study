private void checkFile(Path file) throws IOException {
  if (file == null || !file.getFileName().toString().toLowerCase(Locale.ROOT).endsWith(".md")) {
    return;
  }
  LOG.finer("Checking " + file);
  int lineNo=0;
  for (  String line : Files.readAllLines(file,StandardCharsets.UTF_8)) {
    lineNo++;
    Matcher ruleTagMatcher=RULE_TAG.matcher(line);
    while (ruleTagMatcher.find()) {
      String ruleReference=ruleTagMatcher.group(1);
      int pos=ruleTagMatcher.end();
      if (line.charAt(pos) != '%' || line.charAt(pos + 1) != '}') {
        addIssue(file,lineNo,"Rule tag for " + ruleReference + " is not closed properly");
      }
 else       if (!ruleReferenceTargetExists(ruleReference)) {
        addIssue(file,lineNo,"Rule " + ruleReference + " is not found");
      }
    }
  }
}
