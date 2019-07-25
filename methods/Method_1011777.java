List<VcsFileRevision> revisions(){
synchronized (myFilteredRevisions) {
    return new ArrayList<VcsFileRevision>(myFilteredRevisions);
  }
}
