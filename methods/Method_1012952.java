@Override public void validate(){
  assertTrue(instructions != null,"Instructions cannot be null");
  assertTrue(submissionStartTimestamp > 0L,"Start timestamp should be more than zero");
  assertTrue(submissionEndTimestamp > 0L,"End timestamp should be more than zero");
  assertTrue(sessionVisibleSetting != null,"sessionVisibleSetting cannot be null");
  if (sessionVisibleSetting == SessionVisibleSetting.CUSTOM) {
    assertTrue(customSessionVisibleTimestamp != null,"session visible timestamp should not be null");
    assertTrue(customSessionVisibleTimestamp > 0L,"session visible timestamp should be more than zero");
  }
  assertTrue(responseVisibleSetting != null,"responseVisibleSetting cannot be null");
  if (responseVisibleSetting == ResponseVisibleSetting.CUSTOM) {
    assertTrue(customResponseVisibleTimestamp != null,"response visible timestamp should not be null");
    assertTrue(customResponseVisibleTimestamp > 0L,"response visible timestamp should be more than zero");
  }
}
