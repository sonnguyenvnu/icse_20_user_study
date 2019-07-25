public boolean declares(final UniqueString us){
  if (getRootModule() != null) {
    final ExternalModuleTable externalModuleTable=getRootModule().getExternalModuleTable();
    final Context context=externalModuleTable.getContextForRootModule();
    if (context != null) {
      return context.occurSymbol(us);
    }
  }
  return false;
}
