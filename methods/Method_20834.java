public static @Nullable Metadata metadataForProject(final @NonNull Project project){
  if (project.isBacking()) {
    return Metadata.BACKING;
  }
 else   if (project.isStarred()) {
    return Metadata.SAVING;
  }
 else   if (project.isFeaturedToday()) {
    return Metadata.CATEGORY_FEATURED;
  }
  return null;
}
