public void download(Collection<Long> userIds){
  final Map<Long,String> userAvatars=downloadUserAvatarUrls(userIds);
  downloadUserAvatars(userAvatars);
}
