/** 
 * Run an entry fetcher from the command line.
 * @param fetchCommand A string containing both the name of the fetcher to use andthe search query, separated by a :
 * @return A parser result containing the entries fetched or null if an error occurred.
 */
private Optional<ParserResult> fetch(String fetchCommand){
  if ((fetchCommand == null) || !fetchCommand.contains(":") || (fetchCommand.split(":").length != 2)) {
    System.out.println(Localization.lang("Expected syntax for --fetch='<name of fetcher>:<query>'"));
    System.out.println(Localization.lang("The following fetchers are available:"));
    return Optional.empty();
  }
  String[] split=fetchCommand.split(":");
  String engine=split[0];
  String query=split[1];
  List<SearchBasedFetcher> fetchers=WebFetchers.getSearchBasedFetchers(Globals.prefs.getImportFormatPreferences());
  Optional<SearchBasedFetcher> selectedFetcher=fetchers.stream().filter(fetcher -> fetcher.getName().equalsIgnoreCase(engine)).findFirst();
  if (!selectedFetcher.isPresent()) {
    System.out.println(Localization.lang("Could not find fetcher '%0'",engine));
    System.out.println(Localization.lang("The following fetchers are available:"));
    fetchers.forEach(fetcher -> System.out.println("  " + fetcher.getName()));
    return Optional.empty();
  }
 else {
    System.out.println(Localization.lang("Running query '%0' with fetcher '%1'.",query,engine));
    System.out.print(Localization.lang("Please wait..."));
    try {
      List<BibEntry> matches=selectedFetcher.get().performSearch(query);
      if (matches.isEmpty()) {
        System.out.println("\r" + Localization.lang("No results found."));
        return Optional.empty();
      }
 else {
        System.out.println("\r" + Localization.lang("Found %0 results.",String.valueOf(matches.size())));
        return Optional.of(new ParserResult(matches));
      }
    }
 catch (    FetcherException e) {
      LOGGER.error("Error while fetching",e);
      return Optional.empty();
    }
  }
}
