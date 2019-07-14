@Override public UserList updateUserList(String ownerScreenName,String slug,String newListName,boolean isPublicList,String newDescription) throws TwitterException {
  return updateUserList(newListName,isPublicList,newDescription,new HttpParameter("owner_screen_name",ownerScreenName),new HttpParameter("slug",slug));
}
