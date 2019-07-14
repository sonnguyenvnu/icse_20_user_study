@Override public boolean isConsolidated(){
  return super.isConsolidated(KCVEntryMutation.ENTRY2COLUMN_FCT,Functions.identity());
}
