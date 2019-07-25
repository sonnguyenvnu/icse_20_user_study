/** 
 * Modify entry and uniquefier arrays to facilitate a grouped presentation of uniquefied entries.
 * @param entries     The entry array.
 * @param uniquefiers The uniquefier array.
 * @param from        The first index to group (inclusive)
 * @param to          The last index to group (inclusive)
 */
private void group(List<BibEntry> entries,String[] uniquefiers,int from,int to){
  String separator=getStringCitProperty(UNIQUEFIER_SEPARATOR);
  StringBuilder sb=new StringBuilder(uniquefiers[from]);
  for (int i=from + 1; i <= to; i++) {
    sb.append(separator);
    sb.append(uniquefiers[i]);
    entries.set(i,null);
  }
  uniquefiers[from]=sb.toString();
}
