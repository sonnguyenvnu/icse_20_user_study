/** 
 * Returns a namepile for a list of friends.
 */
public static @NonNull String projectCardFriendNamepile(final @NonNull Context context,final @NonNull List<User> friends,final @NonNull KSString ksString){
  final String friendName;
  if (friends.size() < 3) {
    friendName=friends.size() >= 1 ? friends.get(0).name() : "";
  }
 else {
    final String delimiter=context.getString(R.string.project_social_friends_separator).trim() + " ";
    final String joinedFriends=TextUtils.join(delimiter,Arrays.asList(friends.get(0).name(),friends.get(1).name()));
    friendName=friends.size() == 3 ? joinedFriends.concat(delimiter.trim()) : joinedFriends;
  }
  final String secondFriendName;
  if (friends.size() >= 2) {
    secondFriendName=friends.size() == 2 ? friends.get(1).name() : friends.get(2).name();
  }
 else {
    secondFriendName="";
  }
  final String remainingCount=NumberUtils.format(Math.max(0,friends.size() - 3));
  return ksString.format("discovery_baseball_card_social_friends_are_backers",friends.size() == 3 ? friends.size() - 1 : friends.size(),"friend_name",friendName,"second_friend_name",secondFriendName,"remaining_count",remainingCount);
}
