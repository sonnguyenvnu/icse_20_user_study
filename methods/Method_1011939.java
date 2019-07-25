@NotNull public List<String> read(){
  List<String> result=new ArrayList<>();
  if (myAdditionalCPFile.exists()) {
    try (Scanner sc=new Scanner(myAdditionalCPFile,Charset.defaultCharset().name())){
      boolean skipMode=false;
      while (sc.hasNextLine()) {
        String line=sc.nextLine().trim();
        if (line.startsWith(":")) {
          skipMode=myTypes != null && !myTypes.contains(line.substring(1));
          continue;
        }
        if (!skipMode) {
          result.add(line);
        }
      }
    }
 catch (    FileNotFoundException ignored) {
      LOG.error("Problem while parsing class path",ignored);
    }
  }
 else {
    LOG.debug("The file with additional class path could not be found");
  }
  return result;
}
