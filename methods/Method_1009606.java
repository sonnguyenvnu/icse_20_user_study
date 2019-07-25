public String generate(Event event) throws Exception {
  return XMLUtil.documentToFragmentString(buildDOM(event));
}
