private static void putBundle(@NonNull Gist gistsModel,@NonNull Intent starter){
  String login=gistsModel.getOwner() != null ? gistsModel.getOwner().getLogin() : gistsModel.getUser() != null ? gistsModel.getUser().getLogin() : "";
  starter.putExtras(Bundler.start().putParcelableArrayList(BundleConstant.ITEM,gistsModel.getFilesAsList()).put(BundleConstant.EXTRA,Login.getUser().getLogin().equalsIgnoreCase(login)).put(BundleConstant.ID,gistsModel.getGistId()).put(BundleConstant.EXTRA_TWO,gistsModel.getDescription()).end());
}
