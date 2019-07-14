/** 
 * Updates all the flagged modes/tools.
 */
static private void updateFlagged(Base base,File root) throws Exception {
  File[] markedForUpdate=root.listFiles(new FileFilter(){
    public boolean accept(    File folder){
      return (folder.isDirectory() && LocalContribution.isUpdateFlagged(folder));
    }
  }
);
  List<String> updateContribsNames=new ArrayList<>();
  List<AvailableContribution> updateContribsList=new LinkedList<>();
  String type=root.getName().substring(root.getName().lastIndexOf('/') + 1);
  String propFileName=null;
  if (type.equalsIgnoreCase("tools"))   propFileName="tool.properties";
 else   if (type.equalsIgnoreCase("modes"))   propFileName="mode.properties";
 else   if (type.equalsIgnoreCase("libraries"))   propFileName="libraries.properties";
  for (  File folder : markedForUpdate) {
    StringDict props=Util.readSettings(new File(folder,propFileName));
    updateContribsNames.add(props.get("name"));
    Util.removeDir(folder);
  }
  Iterator<AvailableContribution> iter=listing.advertisedContributions.iterator();
  while (iter.hasNext()) {
    AvailableContribution availableContribs=iter.next();
    if (updateContribsNames.contains(availableContribs.getName())) {
      updateContribsList.add(availableContribs);
    }
  }
  Iterator<AvailableContribution> iter2=updateContribsList.iterator();
  while (iter2.hasNext()) {
    AvailableContribution contribToUpdate=iter2.next();
    installOnStartUp(base,contribToUpdate);
    listing.replaceContribution(contribToUpdate,contribToUpdate);
  }
}
