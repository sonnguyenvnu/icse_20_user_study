public void enable(String typeName,boolean isChanged){
  if (EntryTypes.getStandardType(typeName,mode).isPresent()) {
    Optional<EntryType> entryType=EntryTypes.getType(typeName,mode);
    if (isChanged || (entryType.isPresent() && (entryType.get() instanceof CustomEntryType))) {
      def.setEnabled(true);
    }
 else {
      def.setEnabled(false);
    }
    remove.setEnabled(false);
  }
 else {
    def.setEnabled(false);
    remove.setEnabled(true);
  }
}
