@Override protected void content(HTMLElement body) throws IOException {
  if (bundle.getPackages().isEmpty()) {
    body.p().text("No class files specified.");
  }
 else   if (!bundle.containsCode()) {
    body.p().text("None of the analyzed classes contain code relevant for code coverage.");
  }
 else {
    super.content(body);
  }
}
