/** 
 * ??s
 * @param audioInfos
 */
public boolean add(List<AudioInfo> audioInfos){
  List<ContentValues> values=new ArrayList<ContentValues>();
  for (  AudioInfo audioInfo : audioInfos) {
    values.add(getContentValues(audioInfo));
  }
  return insert(values);
}
