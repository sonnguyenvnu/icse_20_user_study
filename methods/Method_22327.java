public void purgeOldData(Calendar keepAfter){
  for (final Iterator<ReportMetadata> iterator=list.iterator(); iterator.hasNext(); ) {
    if (keepAfter.after(iterator.next().getTimestamp())) {
      iterator.remove();
    }
  }
}
