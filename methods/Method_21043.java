/** 
 * Returns a  {@link DiscoveryParams} constructed by parsing data out of the given {@link Uri}.
 */
public static @NonNull DiscoveryParams fromUri(final @NonNull Uri uri){
  Builder builder=DiscoveryParams.builder();
  if (KSUri.isDiscoverCategoriesPath(uri.getPath())) {
    builder=builder.categoryParam(uri.getLastPathSegment());
  }
  if (KSUri.isDiscoverPlacesPath(uri.getPath())) {
    builder=builder.locationParam(uri.getLastPathSegment());
  }
  if (KSUri.isDiscoverScopePath(uri.getPath(),"ending-soon")) {
    builder=builder.sort(Sort.ENDING_SOON);
  }
  if (KSUri.isDiscoverScopePath(uri.getPath(),"newest")) {
    builder=builder.sort(Sort.NEWEST).staffPicks(true);
  }
  if (KSUri.isDiscoverScopePath(uri.getPath(),"popular")) {
    builder=builder.sort(Sort.POPULAR);
  }
  if (KSUri.isDiscoverScopePath(uri.getPath(),"recently-launched")) {
    builder=builder.sort(Sort.NEWEST);
  }
  if (KSUri.isDiscoverScopePath(uri.getPath(),"small-projects")) {
    builder=builder.pledged(0);
  }
  if (KSUri.isDiscoverScopePath(uri.getPath(),"social")) {
    builder=builder.social(0);
  }
  if (KSUri.isDiscoverScopePath(uri.getPath(),"successful")) {
    builder=builder.sort(Sort.ENDING_SOON).state(State.SUCCESSFUL);
  }
  final Integer backed=ObjectUtils.toInteger(uri.getQueryParameter("backed"));
  if (backed != null) {
    builder=builder.backed(backed);
  }
  final String categoryParam=uri.getQueryParameter("category_id");
  if (categoryParam != null) {
    builder=builder.categoryParam(categoryParam);
  }
  final String locationParam=uri.getQueryParameter("woe_id");
  if (locationParam != null) {
    builder=builder.locationParam(locationParam);
  }
  final Integer page=ObjectUtils.toInteger(uri.getQueryParameter("page"));
  if (page != null) {
    builder=builder.page(page);
  }
  final Integer perPage=ObjectUtils.toInteger(uri.getQueryParameter("per_page"));
  if (perPage != null) {
    builder=builder.perPage(perPage);
  }
  final Integer pledged=ObjectUtils.toInteger(uri.getQueryParameter("pledged"));
  if (pledged != null) {
    builder=builder.pledged(pledged);
  }
  final Boolean recommended=ObjectUtils.toBoolean(uri.getQueryParameter("recommended"));
  if (recommended != null) {
    builder=builder.recommended(recommended);
  }
  final Integer social=ObjectUtils.toInteger(uri.getQueryParameter("social"));
  if (social != null) {
    builder=builder.social(social);
  }
  final Boolean staffPicks=ObjectUtils.toBoolean(uri.getQueryParameter("staff_picks"));
  if (staffPicks != null) {
    builder=builder.staffPicks(staffPicks);
  }
  final String sortParam=uri.getQueryParameter("sort");
  if (sortParam != null) {
    builder=builder.sort(Sort.fromString(sortParam));
  }
  final Integer starred=ObjectUtils.toInteger(uri.getQueryParameter("starred"));
  if (starred != null) {
    builder=builder.starred(starred);
  }
  final String stateParam=uri.getQueryParameter("state");
  if (stateParam != null) {
    builder=builder.state(State.fromString(stateParam));
  }
  final String term=uri.getQueryParameter("term");
  if (term != null) {
    builder=builder.term(term);
  }
  return builder.build();
}
