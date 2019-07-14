/** 
 * Builds tag objects with the specified tags string.
 * @param tagsStr the specified tags string
 * @return tag objects
 */
public List<JSONObject> buildTagObjs(final String tagsStr){
  final List<JSONObject> ret=new ArrayList<>();
  final String[] tagTitles=tagsStr.split(",");
  for (  final String tagTitle : tagTitles) {
    final JSONObject tag=new JSONObject();
    tag.put(Tag.TAG_TITLE,tagTitle);
    final String uri=tagRepository.getURIByTitle(tagTitle);
    if (null != uri) {
      tag.put(Tag.TAG_URI,uri);
    }
 else {
      tag.put(Tag.TAG_URI,tagTitle);
    }
    Tag.fillDescription(tag);
    ret.add(tag);
  }
  return ret;
}
