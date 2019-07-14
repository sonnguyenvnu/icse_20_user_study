@Override TerracottaJobStoreExtensions getRealStore(ToolkitInternal toolkit){
  return new PlainTerracottaJobStore(toolkit);
}
