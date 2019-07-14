/** 
 * Makes the specified user to article, comments notifications as read.
 * @param userId     the specified user id
 * @param articleId  the specified article id
 * @param commentIds the specified comment ids
 */
public void makeRead(final String userId,final String articleId,final List<String> commentIds){
  final List<String> dataIds=new ArrayList<>(commentIds);
  dataIds.add(articleId);
  makeRead(userId,dataIds);
}
