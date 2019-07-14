private void replaceFiles(String path,ArrayList<HybridFileParcelable> filesToCopy,ArrayList<HybridFileParcelable> conflictingFiles){
  if (counter < conflictingFiles.size()) {
    if (dialogState != null) {
      counter++;
    }
 else {
      counter=conflictingFiles.size();
    }
  }
  onEndDialog(path,filesToCopy,conflictingFiles);
}
