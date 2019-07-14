/** 
 * Constructs a cookie for the given cookie value and project. This method can return `null` if a cookie cannot be constructed, e.g. the project has a malformed project url.
 */
private static @Nullable HttpCookie buildCookieWithValueAndProject(final @NonNull String cookieValue,final @NonNull Project project){
  final HttpCookie cookie=new HttpCookie(cookieNameForProject(project),cookieValue);
  try {
    final URL url=new URL(project.webProjectUrl());
    cookie.setPath(url.getPath());
    cookie.setDomain(url.getHost());
  }
 catch (  MalformedURLException e) {
    return null;
  }
  cookie.setVersion(0);
  final DateTime deadline=project.deadline();
  if (deadline != null) {
    cookie.setMaxAge(ProjectUtils.timeInSecondsUntilDeadline(project));
  }
 else {
    cookie.setMaxAge(new DateTime().plusDays(10).getMillis() / 1000l);
  }
  return cookie;
}
