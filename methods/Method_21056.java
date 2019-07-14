public static @NonNull String userAgent(final @NonNull Build build){
  return new StringBuilder().append("Kickstarter Android Mobile Variant/").append(build.variant()).append(" Code/").append(build.versionCode()).append(" Version/").append(build.versionName()).toString();
}
