/** 
 * Returns an iterable that allows to iterate over all files in this segments info
 */
public static Iterable<String> files(SegmentInfos infos) throws IOException {
  final List<Collection<String>> list=new ArrayList<>();
  list.add(Collections.singleton(infos.getSegmentsFileName()));
  for (  SegmentCommitInfo info : infos) {
    list.add(info.files());
  }
  return Iterables.flatten(list);
}
