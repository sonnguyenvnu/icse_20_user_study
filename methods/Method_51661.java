protected void optimize(){
  final Matcher matcher=ENDS_WITH.matcher(this.regex);
  if (matcher.matches()) {
    final String literalPath=matcher.group(1);
    final String fileExtension=matcher.group(2);
    if (fileExtension != null) {
      this.endsWith=literalPath + fileExtension;
    }
 else {
      this.endsWith=literalPath;
    }
  }
 else {
    try {
      this.pattern=Pattern.compile(this.regex);
    }
 catch (    PatternSyntaxException ignored) {
    }
  }
}
