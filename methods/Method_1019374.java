private List<Section> add(SoftwareSystem softwareSystem,File directory) throws IOException {
  List<Section> sections=new ArrayList<>();
  if (!directory.exists()) {
    throw new IllegalArgumentException(directory.getCanonicalPath() + " does not exist.");
  }
  if (!directory.isDirectory()) {
    throw new IllegalArgumentException(directory.getCanonicalPath() + " is not a directory.");
  }
  File[] filesInDirectory=directory.listFiles();
  if (filesInDirectory != null) {
    Arrays.sort(filesInDirectory);
    for (    File file : filesInDirectory) {
      Format format=FormatFinder.findFormat(file);
      String sectionDefinition="";
      if (format == Format.Markdown) {
        sectionDefinition="##";
      }
 else       if (format == Format.AsciiDoc) {
        sectionDefinition="==";
      }
      String content=new String(Files.readAllBytes(file.toPath()),"UTF-8");
      String sectionName=file.getName();
      Matcher matcher=Pattern.compile("^" + sectionDefinition + " (.*?)$",Pattern.MULTILINE).matcher(content);
      if (matcher.find()) {
        sectionName=matcher.group(1);
      }
      Section section=addSection(softwareSystem,sectionName,format,content);
      sections.add(section);
    }
  }
  return sections;
}
