public void refresh(){
  cachedContents=printSources.stream().map(PrintSource::getPrintContent).collect(Collectors.toList());
}
