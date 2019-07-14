/** 
 * Sets  {@link AndTerm} as searchTerm.
 * @param searchTerm {@link SearchTerm} to set as AND.
 */
protected void and(final SearchTerm searchTerm){
  if (this.searchTerm == null) {
    this.searchTerm=searchTerm;
    return;
  }
  this.searchTerm=new AndTerm(this.searchTerm,searchTerm);
}
