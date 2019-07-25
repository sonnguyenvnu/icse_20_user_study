/** 
 * Reinitialize the fields has to be run in the UI thread
 */
private synchronized void reinit(){
  disposeLock.lock();
  try {
    if (getPartControl().isDisposed()) {
      return;
    }
    m_startTimestamp=0;
    m_startLabel.setText("");
    m_lastCheckpointLabel.setText("");
    m_finishLabel.setText("");
    m_tlcSimulationLabel.setVisible(false);
    m_tlcSearchModeLabel.setText("");
    m_tlcStatusLabel.setText(TLCModelLaunchDataProvider.NOT_RUNNING);
    m_errorStatusHyperLink.setText(TLCModelLaunchDataProvider.NO_ERRORS);
    m_errorStatusHyperLink.setVisible(false);
    m_fingerprintCollisionLabel.setText("");
    m_fingerprintCollisionLabel.setVisible(false);
    m_zeroCoverageLabel.setVisible(false);
    coverage.setInput(new Vector<CoverageInformationItem>());
    stateSpace.setInput(new Vector<StateSpaceInformationItem>());
    progressOutput.setDocument(new Document(TLCModelLaunchDataProvider.NO_OUTPUT_AVAILABLE));
    userOutput.setDocument(new Document(TLCModelLaunchDataProvider.NO_OUTPUT_AVAILABLE));
    setErrorPaneVisible(false);
    m_generalTopPane.layout(true,true);
  }
  finally {
    disposeLock.unlock();
  }
}
