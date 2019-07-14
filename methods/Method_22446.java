public boolean isCompatible(int versionNum){
  return ((maxRevision == 0 || versionNum <= maxRevision) && versionNum >= minRevision);
}
