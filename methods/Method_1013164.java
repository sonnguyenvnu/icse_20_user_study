/** 
 * Save data back to config
 */
@Override public void commit(final boolean onSave){
  final Model model=getModel();
  final boolean isMCMode=m_modelCheckModeOption.getSelection();
  model.setAttribute(LAUNCH_MC_MODE,isMCMode);
  final boolean isDFIDMode=m_depthFirstOptionCheckbox.getSelection();
  model.setAttribute(LAUNCH_DFID_MODE,isDFIDMode);
  final int dfidDepth=Integer.parseInt(m_simulationDepthText.getText());
  final int simuDepth=Integer.parseInt(m_simulationDepthText.getText());
  int simuAril=LAUNCH_SIMU_ARIL_DEFAULT;
  int simuSeed=LAUNCH_SIMU_SEED_DEFAULT;
  if (!"".equals(m_simulationArilText.getText())) {
    simuAril=Integer.parseInt(m_simulationArilText.getText());
  }
  if (!"".equals(m_simulationSeedText.getText())) {
    simuSeed=Integer.parseInt(m_simulationSeedText.getText());
  }
  model.setAttribute(LAUNCH_DFID_DEPTH,dfidDepth);
  model.setAttribute(LAUNCH_SIMU_DEPTH,simuDepth);
  model.setAttribute(LAUNCH_SIMU_SEED,simuSeed);
  model.setAttribute(LAUNCH_SIMU_ARIL,simuAril);
  model.setAttribute(LAUNCH_DEFER_LIVENESS,m_deferLivenessCheckbox.getSelection());
  model.setAttribute(LAUNCH_RECOVER,m_checkpointRecoverCheckbox.getSelection());
  model.setAttribute(LAUNCH_FP_INDEX_RANDOM,m_randomFingerprintCheckbox.getSelection());
  model.setAttribute(LAUNCH_FP_INDEX,m_fingerprintSeedIndex.getSelection());
  model.setAttribute(LAUNCH_FPBITS,m_fingerprintBits.getSelection());
  model.setAttribute(LAUNCH_MAXSETSIZE,m_maxSetSize.getSelection());
  model.setAttribute(LAUNCH_VISUALIZE_STATEGRAPH,m_visualizeStateGraphCheckbox.getSelection());
  final Object coverage=m_collectCoverageCombo.getStructuredSelection().getFirstElement();
  if (coverage instanceof Coverage) {
    model.setCoverage((Coverage)coverage);
  }
  String viewFormula=FormHelper.trimTrailingSpaces(m_viewSource.getDocument().get());
  model.setAttribute(LAUNCH_VIEW,viewFormula);
  final String vmArgs=m_extraVMArgumentsText.getText().replace("\r\n"," ").replace("\n"," ");
  model.setAttribute(LAUNCH_JVM_ARGS,vmArgs);
  final String tlcParameters=m_extraTLCParametersText.getText();
  model.setAttribute(LAUNCH_TLC_PARAMETERS,tlcParameters);
  super.commit(onSave);
}
