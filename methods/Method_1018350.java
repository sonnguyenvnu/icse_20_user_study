/** 
 * Add a link to the  {@link ProfileController}'s base URI to the app's root URI.
 * @param resource
 * @return
 */
@Override public RepositoryLinksResource process(RepositoryLinksResource resource){
  resource.add(new Link(ProfileController.getRootPath(this.configuration),PROFILE_REL));
  return resource;
}
