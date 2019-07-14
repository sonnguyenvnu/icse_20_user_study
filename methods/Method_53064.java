/** 
 * static factory method for twitter-text-java
 * @return hashtag entity
 * @since Twitter4J 2.2.6
 */
public static HashtagEntity createHashtagEntity(int start,int end,String text){
  return new HashtagEntityJSONImpl(start,end,text);
}
