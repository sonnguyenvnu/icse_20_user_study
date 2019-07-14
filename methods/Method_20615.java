/** 
 * Tracks a project show event.
 * @param intentRefTag (nullable) The ref tag present in the activity upon displaying the project.
 * @param cookieRefTag (nullable) The ref tag extracted from the cookie store upon viewing the project.
 */
public void trackProjectShow(final @NonNull Project project,final @Nullable RefTag intentRefTag,final @Nullable RefTag cookieRefTag){
  final Map<String,Object> properties=KoalaUtils.projectProperties(project,this.client.loggedInUser());
  if (intentRefTag != null) {
    properties.put("ref_tag",intentRefTag.tag());
  }
  if (cookieRefTag != null) {
    properties.put("referrer_credit",cookieRefTag.tag());
  }
  this.client.track(KoalaEvent.PROJECT_PAGE,properties);
  this.client.track(KoalaEvent.VIEWED_PROJECT_PAGE,properties);
}
