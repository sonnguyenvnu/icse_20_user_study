/** 
 * Returns a list of snapshots from repository sorted by snapshot creation date
 * @param repositoryName repository name
 * @param snapshotIds       snapshots for which to fetch snapshot information
 * @param incompatibleSnapshotIds   snapshots for which not to fetch snapshot information
 * @param ignoreUnavailable if true, snapshots that could not be read will only be logged with a warning,if false, they will throw an error
 * @return list of snapshots
 */
public List<SnapshotInfo> snapshots(final String repositoryName,final List<SnapshotId> snapshotIds,final Set<SnapshotId> incompatibleSnapshotIds,final boolean ignoreUnavailable){
  final Set<SnapshotInfo> snapshotSet=new HashSet<>();
  final Set<SnapshotId> snapshotIdsToIterate=new HashSet<>(snapshotIds);
  final List<SnapshotsInProgress.Entry> entries=currentSnapshots(repositoryName,snapshotIdsToIterate.stream().map(SnapshotId::getName).collect(Collectors.toList()));
  for (  SnapshotsInProgress.Entry entry : entries) {
    snapshotSet.add(inProgressSnapshot(entry));
    snapshotIdsToIterate.remove(entry.snapshot().getSnapshotId());
  }
  final Repository repository=repositoriesService.repository(repositoryName);
  for (  SnapshotId snapshotId : snapshotIdsToIterate) {
    try {
      if (incompatibleSnapshotIds.contains(snapshotId)) {
        snapshotSet.add(SnapshotInfo.incompatible(snapshotId));
      }
 else {
        snapshotSet.add(repository.getSnapshotInfo(snapshotId));
      }
    }
 catch (    Exception ex) {
      if (ignoreUnavailable) {
        logger.warn((Supplier<?>)() -> new ParameterizedMessage("failed to get snapshot [{}]",snapshotId),ex);
      }
 else {
        throw new SnapshotException(repositoryName,snapshotId,"Snapshot could not be read",ex);
      }
    }
  }
  final ArrayList<SnapshotInfo> snapshotList=new ArrayList<>(snapshotSet);
  CollectionUtil.timSort(snapshotList);
  return Collections.unmodifiableList(snapshotList);
}
