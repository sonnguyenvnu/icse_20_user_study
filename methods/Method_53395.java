public PagableResponseList<UserList> getUserListsOwnerships(String listOwnerScreenName,long cursor) throws TwitterException {
  return getUserListsOwnerships(listOwnerScreenName,20,cursor);
}
