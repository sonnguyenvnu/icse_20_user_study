public void paged(Paged paged){
  criteria.paged(paged);
  DataPermission.Chain.onBuild(criteria,paged);
}
