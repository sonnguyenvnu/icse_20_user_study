private void startShareOnFacebook(final @NonNull Project project){
  if (!ShareDialog.canShow(ShareLinkContent.class)) {
    return;
  }
  final Photo photo=project.photo();
  final ShareOpenGraphObject object=new ShareOpenGraphObject.Builder().putString("og:type","kickstarter:project").putString("og:title",project.name()).putString("og:description",project.blurb()).putString("og:image",photo == null ? null : photo.small()).putString("og:url",project.webProjectUrl()).build();
  final ShareOpenGraphAction action=new ShareOpenGraphAction.Builder().setActionType("kickstarter:back").putObject("project",object).build();
  final ShareOpenGraphContent content=new ShareOpenGraphContent.Builder().setPreviewPropertyName("project").setAction(action).build();
  this.shareDialog.show(content);
}
