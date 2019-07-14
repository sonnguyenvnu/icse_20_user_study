@Override void addElements(ArrayList<CompressedObjectParcelable> elements){
  try {
    Archive zipfile=new Archive(new File(fileLocation));
    String relativeDirDiffSeparator=relativeDirectory.replace(CompressedHelper.SEPARATOR,"\\");
    for (    FileHeader rarArchive : zipfile.getFileHeaders()) {
      String name=rarArchive.getFileNameString();
      if (!CompressedHelper.isEntryPathValid(name)) {
        continue;
      }
      boolean isInBaseDir=(relativeDirDiffSeparator == null || relativeDirDiffSeparator.equals("")) && !name.contains("\\");
      boolean isInRelativeDir=relativeDirDiffSeparator != null && name.contains("\\") && name.substring(0,name.lastIndexOf("\\")).equals(relativeDirDiffSeparator);
      if (isInBaseDir || isInRelativeDir) {
        elements.add(new CompressedObjectParcelable(RarDecompressor.convertName(rarArchive),0,rarArchive.getDataSize(),rarArchive.isDirectory()));
      }
    }
  }
 catch (  RarException|IOException e) {
    e.printStackTrace();
  }
}
