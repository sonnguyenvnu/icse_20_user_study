/** 
 * {@inheritDoc}
 */
@Override public void uninstall(final E c){
  removeBreadcrumbAdapter();
  containerAdapter=null;
  c.removeAncestorListener(ancestorAdapter);
  ancestorAdapter=null;
}
