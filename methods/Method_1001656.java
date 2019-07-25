public FontConfiguration hyperlink(){
  if (useUnderlineForHyperlink) {
    return add(FontStyle.UNDERLINE).withHyperlink();
  }
  return withHyperlink();
}
