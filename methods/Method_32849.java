@Override public Cursor queryRoots(String[] projection) throws FileNotFoundException {
  final MatrixCursor result=new MatrixCursor(projection != null ? projection : DEFAULT_ROOT_PROJECTION);
  @SuppressWarnings("ConstantConditions") final String applicationName=getContext().getString(R.string.application_name);
  final MatrixCursor.RowBuilder row=result.newRow();
  row.add(Root.COLUMN_ROOT_ID,getDocIdForFile(BASE_DIR));
  row.add(Root.COLUMN_DOCUMENT_ID,getDocIdForFile(BASE_DIR));
  row.add(Root.COLUMN_SUMMARY,null);
  row.add(Root.COLUMN_FLAGS,Root.FLAG_SUPPORTS_CREATE | Root.FLAG_SUPPORTS_SEARCH);
  row.add(Root.COLUMN_TITLE,applicationName);
  row.add(Root.COLUMN_MIME_TYPES,ALL_MIME_TYPES);
  row.add(Root.COLUMN_AVAILABLE_BYTES,BASE_DIR.getFreeSpace());
  row.add(Root.COLUMN_ICON,R.drawable.ic_launcher);
  return result;
}
