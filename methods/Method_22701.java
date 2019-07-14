public void getSketchCodeFiles(List<String> outFilenames,List<String> outExtensions){
  String list[]=folder.list();
  for (  String filename : list) {
    if (filename.startsWith("."))     continue;
    if (new File(folder,filename).isDirectory())     continue;
    String base=filename;
    for (    String extension : mode.getExtensions()) {
      if (base.toLowerCase().endsWith("." + extension)) {
        base=base.substring(0,base.length() - (extension.length() + 1));
        if (isSanitaryName(base)) {
          if (outFilenames != null)           outFilenames.add(filename);
          if (outExtensions != null)           outExtensions.add(extension);
        }
      }
    }
  }
}
