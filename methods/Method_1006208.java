/** 
 * Input: entry type as a string
 */
@Override public String format(String entryType){
  BibEntry entry=new BibEntry();
  entry.setType(entryType);
  TypedBibEntry typedEntry=new TypedBibEntry(entry,BibDatabaseMode.BIBLATEX);
  return typedEntry.getTypeForDisplay();
}
