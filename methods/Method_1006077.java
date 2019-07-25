/** 
 * Unabbreviate the journal name of the given entry.
 * @param entry     The entry to be treated.
 * @param fieldName The field name (e.g. "journal")
 * @param ce        If the entry is changed, add an edit to this compound.
 * @return true if the entry was changed, false otherwise.
 */
public boolean unabbreviate(BibDatabase database,BibEntry entry,String fieldName,CompoundEdit ce){
  if (!entry.hasField(fieldName)) {
    return false;
  }
  String text=entry.getField(fieldName).get();
  String origText=text;
  if (database != null) {
    text=database.resolveForStrings(text);
  }
  if (!journalAbbreviationRepository.isKnownName(text)) {
    return false;
  }
  if (!journalAbbreviationRepository.isAbbreviatedName(text)) {
    return false;
  }
  Abbreviation abbreviation=journalAbbreviationRepository.getAbbreviation(text).get();
  String newText=abbreviation.getName();
  entry.setField(fieldName,newText);
  ce.addEdit(new UndoableFieldChange(entry,fieldName,origText,newText));
  return true;
}
