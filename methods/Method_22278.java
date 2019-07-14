@Override public void processReports(@NonNull Context context,@NonNull CoreConfiguration config,List<Report> reports){
  if (config.deleteUnapprovedReportsOnApplicationStart()) {
    final List<Report> sort=new ArrayList<>();
    for (    Report report : reports) {
      if (!report.isApproved()) {
        sort.add(report);
      }
    }
    if (!sort.isEmpty()) {
      final LastModifiedComparator comparator=new LastModifiedComparator();
      Collections.sort(sort,(r1,r2) -> comparator.compare(r1.getFile(),r2.getFile()));
      if (config.deleteUnapprovedReportsOnApplicationStart()) {
        for (int i=0; i < sort.size() - 1; i++) {
          sort.get(i).delete();
        }
      }
      sort.get(sort.size() - 1).approve();
    }
  }
}
