/** 
 * Needs to be wrapped in metadataAccess.read
 */
public ServiceLevelAssessment assess(ID id){
  ServiceLevelAgreement sla=agreementProvider.getAgreement(id);
  if (sla == null) {
    throw new RuntimeException("Failed to get sla node for " + id);
  }
  return assess(sla);
}
