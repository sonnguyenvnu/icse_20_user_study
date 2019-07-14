private ArrayList<HybridFileParcelable> checkConflicts(final ArrayList<HybridFileParcelable> filesToCopy,HybridFile destination){
  final ArrayList<HybridFileParcelable> conflictingFiles=new ArrayList<>();
  destination.forEachChildrenFile(context,rootMode,file -> {
    for (    HybridFileParcelable j : filesToCopy) {
      if (file.getName().equals((j).getName())) {
        conflictingFiles.add(j);
      }
    }
  }
);
  return conflictingFiles;
}
