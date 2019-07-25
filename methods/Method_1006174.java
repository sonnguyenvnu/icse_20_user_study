private Optional<URL> search(String url) throws IOException {
  Optional<URL> pdfLink=Optional.empty();
  Document doc=Jsoup.connect(url).userAgent(URLDownload.USER_AGENT).get();
  for (int i=0; i < NUM_RESULTS; i++) {
    Elements link=doc.select(String.format("div[data-rp=%S] div.gs_or_ggsm a",i));
    if (link.first() != null) {
      String target=link.first().attr("href");
      if (!target.isEmpty() && new URLDownload(target).isPdf()) {
        LOGGER.info("Fulltext PDF found @ Google: " + target);
        pdfLink=Optional.of(new URL(target));
        break;
      }
    }
  }
  return pdfLink;
}
