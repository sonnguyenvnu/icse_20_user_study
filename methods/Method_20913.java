public static @NonNull DiscoverEnvelope discoverEnvelope(final @NonNull List<Project> projects){
  return DiscoverEnvelope.builder().projects(projects).urls(DiscoverEnvelope.UrlsEnvelope.builder().api(DiscoverEnvelope.UrlsEnvelope.ApiEnvelope.builder().moreProjects("").build()).build()).build();
}
