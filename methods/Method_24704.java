/** 
 * Returns a list of AvailableContributions of those libraries that the user wants imported, but that are not installed.
 * @param importHeaders
 */
private List<AvailableContribution> getNotInstalledAvailableLibs(ArrayList<String> importHeadersList){
  Map<String,Contribution> importMap=ContributionListing.getInstance().getLibrariesByImportHeader();
  List<AvailableContribution> libList=new ArrayList<>();
  for (  String importHeaders : importHeadersList) {
    int dot=importHeaders.lastIndexOf('.');
    String entry=(dot == -1) ? importHeaders : importHeaders.substring(0,dot);
    if (entry.startsWith("java.") || entry.startsWith("javax.") || entry.startsWith("processing.")) {
      continue;
    }
    Library library=null;
    try {
      library=this.getMode().getLibrary(entry);
      if (library == null) {
        Contribution c=importMap.get(importHeaders);
        if (c != null && c instanceof AvailableContribution) {
          libList.add((AvailableContribution)c);
        }
      }
    }
 catch (    Exception e) {
      Contribution c=importMap.get(importHeaders);
      if (c != null && c instanceof AvailableContribution) {
        libList.add((AvailableContribution)c);
      }
    }
  }
  return libList;
}
