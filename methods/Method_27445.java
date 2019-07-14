private void appendAvatar(@NonNull Event eventsModel){
  if (avatar != null) {
    if (eventsModel.getActor() != null) {
      avatar.setUrl(eventsModel.getActor().getAvatarUrl(),eventsModel.getActor().getLogin(),eventsModel.getActor().isOrganizationType(),LinkParserHelper.isEnterprise(eventsModel.getActor().getHtmlUrl()));
    }
 else {
      avatar.setUrl(null,null,false,false);
    }
  }
}
