public void takeData(final @NonNull ThanksData data){
  setSection(SECTION_SHARE_VIEW,Collections.singletonList(data.getBackedProject()));
  setSection(SECTION_RECOMMENDED_PROJECTS_VIEW,ProjectUtils.combineProjectsAndParams(data.getRecommendedProjects(),DiscoveryParams.builder().build()));
  setSection(SECTION_CATEGORY_VIEW,Collections.singletonList(data.getCategory()));
  notifyDataSetChanged();
}
