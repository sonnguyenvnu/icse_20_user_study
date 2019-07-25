private FilesDelta copy(FilesDelta that){
  if (!(key.contains(that.key))) {
    throw new IllegalArgumentException();
  }
  Set<IFile> newlyTouchedDirs=SetSequence.fromSet(new HashSet<IFile>());
  for (  IFile file : MapSequence.fromMap(that.files).keySet()) {
    FilesDelta.Status newStatus=MapSequence.fromMap(that.files).get(file);
    if (newStatus == FilesDelta.Status.STALE && MapSequence.fromMap(files).containsKey(file)) {
      continue;
    }
 else {
      MapSequence.fromMap(files).put(file,newStatus);
    }
    SetSequence.fromSet(newlyTouchedDirs).addElement((file.isDirectory() ? file : file.getParent()));
  }
  for (  IFile file : MapSequence.fromMap(files).keySet()) {
    if (MapSequence.fromMap(files).get(file) == FilesDelta.Status.STALE && file.isDirectory()) {
      String staleDir=DirUtil.normalizeAsDir(file.getPath());
      for (      IFile touchDir : newlyTouchedDirs) {
        if (DirUtil.startsWith(DirUtil.normalizeAsDir(touchDir.getPath()),staleDir)) {
          MapSequence.fromMap(files).put(file,FilesDelta.Status.KEPT);
          break;
        }
      }
    }
  }
  return this;
}
