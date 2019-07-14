public void trackViewedMessageCreatorModal(final @NonNull Project project){
  final User loggedInUser=this.client.loggedInUser();
  final Map<String,Object> props=KoalaUtils.projectProperties(project,loggedInUser);
  props.put("modal_title","messageCreatorModal");
  this.client.track(KoalaEvent.MODAL_DIALOG_VIEW,props);
}
