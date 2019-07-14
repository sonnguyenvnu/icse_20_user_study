/** 
 * @return list of names of content mounted on this host. 
 */
public List<String> getContentNames(){
  if (mMountItems == null || mMountItems.size() == 0) {
    return Collections.emptyList();
  }
  final int contentSize=mMountItems.size();
  final List<String> contentNames=new ArrayList<>(contentSize);
  for (int i=0; i < contentSize; i++) {
    contentNames.add(getMountItemName(getMountItemAt(i)));
  }
  return contentNames;
}
