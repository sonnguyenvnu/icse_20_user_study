@NonNull public static List<FragmentPagerAdapterModel> buildForProjectColumns(@NonNull List<ProjectColumnModel> models,boolean isCollaborator){
  return Stream.of(models).map(projectColumnModel -> new FragmentPagerAdapterModel("",ProjectColumnFragment.Companion.newInstance(projectColumnModel,isCollaborator),String.valueOf(projectColumnModel.getId()))).toList();
}
