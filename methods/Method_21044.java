public @NonNull Map<String,String> queryParams(){
  return Collections.unmodifiableMap(new HashMap<String,String>(){
{
      if (backed() != null) {
        put("backed",String.valueOf(backed()));
      }
      if (category() != null) {
        put("category_id",String.valueOf(category().id()));
      }
      if (categoryParam() != null) {
        put("category_id",categoryParam());
      }
      if (location() != null) {
        put("woe_id",String.valueOf(location().id()));
      }
      if (locationParam() != null) {
        put("woe_id",locationParam());
      }
      if (page() != null) {
        put("page",String.valueOf(page()));
      }
      if (perPage() != null) {
        put("per_page",String.valueOf(perPage()));
      }
      if (pledged() != null) {
        put("pledged",String.valueOf(pledged()));
      }
      if (recommended() != null) {
        put("recommended",String.valueOf(recommended()));
      }
      if (similarTo() != null) {
        put("similar_to",String.valueOf(similarTo().id()));
      }
      if (starred() != null) {
        put("starred",String.valueOf(starred()));
      }
      if (social() != null) {
        put("social",String.valueOf(social()));
      }
      final Sort sort=sort();
      if (sort != null) {
        put("sort",sort.toString());
      }
      if (staffPicks() != null) {
        put("staff_picks",String.valueOf(staffPicks()));
      }
      final State state=state();
      if (state != null) {
        put("state",state.toString());
      }
      if (term() != null) {
        put("q",term());
      }
      if (shouldIncludeFeatured()) {
        put("include_featured","true");
      }
    }
  }
);
}
