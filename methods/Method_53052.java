/** 
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @author Philip Hachey - philip dot hachey at gmail dot com
 */
static String unescapeAndSlideEntityIncdices(String text,UserMentionEntity[] userMentionEntities,URLEntity[] urlEntities,HashtagEntity[] hashtagEntities,MediaEntity[] mediaEntities){
  int entityIndexesLength=0;
  entityIndexesLength+=userMentionEntities == null ? 0 : userMentionEntities.length;
  entityIndexesLength+=urlEntities == null ? 0 : urlEntities.length;
  entityIndexesLength+=hashtagEntities == null ? 0 : hashtagEntities.length;
  entityIndexesLength+=mediaEntities == null ? 0 : mediaEntities.length;
  EntityIndex[] entityIndexes=new EntityIndex[entityIndexesLength];
  int copyStartIndex=0;
  if (userMentionEntities != null) {
    System.arraycopy(userMentionEntities,0,entityIndexes,copyStartIndex,userMentionEntities.length);
    copyStartIndex+=userMentionEntities.length;
  }
  if (urlEntities != null) {
    System.arraycopy(urlEntities,0,entityIndexes,copyStartIndex,urlEntities.length);
    copyStartIndex+=urlEntities.length;
  }
  if (hashtagEntities != null) {
    System.arraycopy(hashtagEntities,0,entityIndexes,copyStartIndex,hashtagEntities.length);
    copyStartIndex+=hashtagEntities.length;
  }
  if (mediaEntities != null) {
    System.arraycopy(mediaEntities,0,entityIndexes,copyStartIndex,mediaEntities.length);
  }
  Arrays.sort(entityIndexes);
  boolean handlingStart=true;
  int entityIndex=0;
  int delta=0;
  int semicolonIndex;
  String escaped;
  String entity;
  StringBuilder unescaped=new StringBuilder(text.length());
  int textCodePointLength=text.codePointCount(0,text.length());
  int codePoint;
  for (int index=0, twitterIndex=0; index < text.length(); index+=Character.charCount(codePoint), twitterIndex++) {
    codePoint=text.codePointAt(index);
    if (codePoint == '&') {
      semicolonIndex=text.indexOf(";",index);
      if (-1 != semicolonIndex) {
        escaped=text.substring(index,semicolonIndex + 1);
        entity=escapeEntityMap.get(escaped);
        if (entity != null) {
          unescaped.append(entity);
          index=semicolonIndex;
          twitterIndex=text.codePointCount(0,semicolonIndex);
          delta=1 - escaped.length();
        }
 else {
          unescaped.appendCodePoint(codePoint);
        }
      }
 else {
        unescaped.appendCodePoint(codePoint);
      }
    }
 else {
      unescaped.appendCodePoint(codePoint);
    }
    if (entityIndex < entityIndexes.length) {
      if (handlingStart) {
        if (entityIndexes[entityIndex].getStart() == (delta + twitterIndex)) {
          entityIndexes[entityIndex].setStart(unescaped.length() - Character.charCount(text.codePointAt(index)));
          handlingStart=false;
        }
      }
 else       if (entityIndexes[entityIndex].getEnd() == (delta + twitterIndex)) {
        entityIndexes[entityIndex].setEnd(unescaped.length() - Character.charCount(text.codePointAt(index)));
        entityIndex++;
        handlingStart=true;
      }
    }
    delta=0;
  }
  if (entityIndex < entityIndexes.length) {
    if (entityIndexes[entityIndex].getEnd() == textCodePointLength) {
      entityIndexes[entityIndex].setEnd(unescaped.length());
    }
  }
  return unescaped.toString();
}
