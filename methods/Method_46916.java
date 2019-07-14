private void onEndDialog(String path,ArrayList<HybridFileParcelable> filesToCopy,ArrayList<HybridFileParcelable> conflictingFiles){
  if (conflictingFiles != null && counter != conflictingFiles.size() && conflictingFiles.size() > 0) {
    if (dialogState == null)     showDialog(path,filesToCopy,conflictingFiles);
 else     if (dialogState == DO_FOR_ALL_ELEMENTS.DO_NOT_REPLACE)     doNotReplaceFiles(path,filesToCopy,conflictingFiles);
 else     if (dialogState == DO_FOR_ALL_ELEMENTS.REPLACE)     replaceFiles(path,filesToCopy,conflictingFiles);
  }
 else {
    CopyNode c=!copyFolder.hasStarted() ? copyFolder.startCopy() : copyFolder.goToNextNode();
    if (c != null) {
      counter=0;
      paths.add(c.getPath());
      filesToCopyPerFolder.add(c.filesToCopy);
      if (dialogState == null)       onEndDialog(c.path,c.filesToCopy,c.conflictingFiles);
 else       if (dialogState == DO_FOR_ALL_ELEMENTS.DO_NOT_REPLACE)       doNotReplaceFiles(c.path,c.filesToCopy,c.conflictingFiles);
 else       if (dialogState == DO_FOR_ALL_ELEMENTS.REPLACE)       replaceFiles(c.path,c.filesToCopy,c.conflictingFiles);
    }
 else {
      finishCopying(paths,filesToCopyPerFolder);
    }
  }
}
