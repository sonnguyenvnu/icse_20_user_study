/** 
 * We overwrite those fields that aren't proper in the properties file with the curated version from the Processing site. This ensures that things have been cleaned up (for instance, that the "sentence" is really a sentence) and that bad data from the contrib's .properties file doesn't break the manager. However, it also ensures that valid fields in the properties file aren't overwritten, since the properties file may be more recent than the contributions.txt file.
 */
public boolean writePropertiesFile(File propFile){
  try {
    StringDict properties=Util.readSettings(propFile);
    String name=properties.get("name");
    if (name == null || name.isEmpty()) {
      name=getName();
    }
    String category;
    StringList categoryList=parseCategories(properties);
    if (categoryList.size() == 1 && categoryList.get(0).equals(UNKNOWN_CATEGORY)) {
      category=getCategoryStr();
    }
 else {
      category=categoryList.join(",");
    }
    StringList importsList=parseImports(properties);
    String authors=properties.get(AUTHORS_PROPERTY);
    if (authors == null || authors.isEmpty()) {
      authors=getAuthorList();
    }
    String url=properties.get("url");
    if (url == null || url.isEmpty()) {
      url=getUrl();
    }
    String sentence=properties.get("sentence");
    if (sentence == null || sentence.isEmpty()) {
      sentence=getSentence();
    }
    String paragraph=properties.get("paragraph");
    if (paragraph == null || paragraph.isEmpty()) {
      paragraph=getParagraph();
    }
    int version;
    try {
      version=Integer.parseInt(properties.get("version"));
    }
 catch (    NumberFormatException e) {
      version=getVersion();
      System.err.println("The version number for “" + name + "” is not a number.");
      System.err.println("Please contact the author to fix it according to the guidelines.");
    }
    String prettyVersion=properties.get("prettyVersion");
    if (prettyVersion != null && prettyVersion.isEmpty()) {
      prettyVersion=null;
    }
    String compatibleContribsList=null;
    if (getType() == ContributionType.EXAMPLES) {
      compatibleContribsList=properties.get(MODES_PROPERTY);
    }
    long lastUpdated;
    try {
      lastUpdated=Long.parseLong(properties.get("lastUpdated"));
    }
 catch (    NumberFormatException nfe) {
      lastUpdated=getLastUpdated();
    }
    int minRev;
    try {
      minRev=Integer.parseInt(properties.get("minRevision"));
    }
 catch (    NumberFormatException e) {
      minRev=getMinRevision();
    }
    int maxRev;
    try {
      maxRev=Integer.parseInt(properties.get("maxRevision"));
    }
 catch (    NumberFormatException e) {
      maxRev=getMaxRevision();
    }
    if (propFile.delete() && propFile.createNewFile() && propFile.setWritable(true)) {
      PrintWriter writer=PApplet.createWriter(propFile);
      writer.println("name=" + name);
      writer.println("category=" + category);
      writer.println(AUTHORS_PROPERTY + "=" + authors);
      writer.println("url=" + url);
      writer.println("sentence=" + sentence);
      writer.println("paragraph=" + paragraph);
      writer.println("version=" + version);
      if (prettyVersion != null) {
        writer.println("prettyVersion=" + prettyVersion);
      }
      writer.println("lastUpdated=" + lastUpdated);
      writer.println("minRevision=" + minRev);
      writer.println("maxRevision=" + maxRev);
      if ((getType() == ContributionType.LIBRARY || getType() == ContributionType.MODE) && importsList != null) {
        writer.println("imports=" + importsList.join(","));
      }
      if (getType() == ContributionType.EXAMPLES) {
        if (compatibleContribsList != null) {
          writer.println(MODES_PROPERTY + "=" + compatibleContribsList);
        }
      }
      writer.flush();
      writer.close();
    }
    return true;
  }
 catch (  FileNotFoundException e) {
    e.printStackTrace();
  }
catch (  IOException e) {
    e.printStackTrace();
  }
  return false;
}
