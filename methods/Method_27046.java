@NonNull public static List<FragmentPagerAdapterModel> buildForGist(@NonNull Context context,@NonNull Gist gistsModel){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.files),GistFilesListFragment.newInstance(gistsModel.getFilesAsList(),false)),new FragmentPagerAdapterModel(context.getString(R.string.comments),GistCommentsFragment.newInstance(gistsModel.getGistId()))).collect(Collectors.toList());
}
