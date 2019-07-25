private ObligationAssessment assess(Obligation ob){
  ObligationAssessmentBuilderImpl builder=new ObligationAssessmentBuilderImpl(ob);
  @SuppressWarnings("unchecked") ObligationAssessor<Obligation> assessor=(ObligationAssessor<Obligation>)findAssessor(ob);
  Log.debug("Assessing obligation \"{}\" with assessor: {}",assessor);
  assessor.assess(ob,builder);
  return builder.build();
}
