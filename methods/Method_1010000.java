@Commit private void apply(){
  userId=determineUserId(avatarUrl,author);
  preview=generatePreview(content);
  if (StringUtils.isBlank(title)) {
    title=getTitleFromUrl(link);
  }
}
