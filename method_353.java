public List<File> _XXXXX_() throws NoWritableLedgerDirException {
  if (!writableLedgerDirectories.isEmpty()) {
    return writableLedgerDirectories;
  }
  return getDirsAboveUsableThresholdSize(minUsableSizeForEntryLogCreation,true);
}