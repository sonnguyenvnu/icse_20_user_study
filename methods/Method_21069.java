public static boolean isProjectSurveyUri(final @NonNull Uri uri,final @NonNull String webEndpoint){
  return isKickstarterUri(uri,webEndpoint) && PROJECT_SURVEY.matcher(UriUtilsKt.path(uri)).matches();
}
