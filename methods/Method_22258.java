/** 
 * @return Approved reports sorted by creation time.
 */
@NonNull public File[] getApprovedReports(){
  final File[] reports=getApprovedFolder().listFiles();
  if (reports == null) {
    return new File[0];
  }
  Arrays.sort(reports,new LastModifiedComparator());
  return reports;
}
