/** 
 * {@inheritDoc}
 */
@Override public void install(final E c){
  containerAdapter=new ContainerAdapter(){
    @Override public void componentAdded(    final ContainerEvent e){
      if (e.getChild() != c) {
        updateAll();
      }
    }
    @Override public void componentRemoved(    final ContainerEvent e){
      if (e.getChild() != c) {
        updateAll();
      }
    }
  }
;
  ancestorAdapter=new AncestorAdapter(){
    @Override public void ancestorAdded(    final AncestorEvent event){
      final Container container=c.getParent();
      if (container instanceof WebBreadcrumb) {
        breadcrumb=(WebBreadcrumb)container;
        breadcrumb.addContainerListener(containerAdapter);
      }
      updateAll();
    }
    @Override public void ancestorRemoved(    final AncestorEvent event){
      removeBreadcrumbAdapter();
      updateAll();
    }
  }
;
  c.addAncestorListener(ancestorAdapter);
}
