private void doNotReplaceFiles(String path,ArrayList<HybridFileParcelable> filesToCopy,ArrayList<HybridFileParcelable> conflictingFiles){
  if (counter < conflictingFiles.size()) {
    if (dialogState != null) {
      filesToCopy.remove(conflictingFiles.get(counter));
      counter++;
    }
 else {
      for (int j=counter; j < conflictingFiles.size(); j++) {
        filesToCopy.remove(conflictingFiles.get(j));
      }
      counter=conflictingFiles.size();
    }
  }
  onEndDialog(path,filesToCopy,conflictingFiles);
}
