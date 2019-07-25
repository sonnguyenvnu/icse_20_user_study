public ServiceLevelAgreementBuilder builder(ServiceLevelAgreement.ID id){
  try {
    Session session=getSession();
    Node slaNode=session.getNodeByIdentifier(id.toString());
    if (slaNode == null || !slaNode.isNodeType("tba:sla")) {
      throw new MetadataRepositoryException("Failed to get sla node for " + id);
    }
    JcrServiceLevelAgreement sla=new JcrServiceLevelAgreement(slaNode);
    sla.clear();
    return builder(slaNode);
  }
 catch (  RepositoryException e) {
    throw new MetadataRepositoryException("Failed to create an sla node",e);
  }
}
