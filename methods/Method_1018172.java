public ServiceLevelAssessment assess(FeedPrecondition precond){
  ServiceLevelAgreement sla=precond.getAgreement();
  return this.assessor.assess(sla);
}
