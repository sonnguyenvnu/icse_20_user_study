public void generate() throws IOException, URISyntaxException {
  log.info("Generating pageRank data files...");
  init();
  createPageRankNodesDirectly();
  createPageRankLinksDirectly();
  closeGenerator();
}
