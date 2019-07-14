@Override public void onItemSelected(Parcelable item){
  if (item instanceof SimpleUrlsModel) {
    SchemeParser.launchUri(getContext(),Uri.parse(((SimpleUrlsModel)item).getItem()));
  }
 else   if (item instanceof GitCommitModel) {
    GitCommitModel model=(GitCommitModel)item;
    NameParser nameParser=new NameParser(model.getUrl());
    Intent intent=CommitPagerActivity.createIntent(getContext(),nameParser.getName(),nameParser.getUsername(),model.getSha(),true,LinkParserHelper.isEnterprise(model.getUrl()));
    getContext().startActivity(intent);
  }
}
