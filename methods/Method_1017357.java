@Override public void apply() throws ConfigurationException {
  Settings settings=Settings.getInstance(project);
  settings.profilerLocalEnabled=radioLocalProfiler.isSelected();
  settings.profilerLocalUrl=textLocalProfilerUrl.getText();
  settings.profilerCsvPath=textLocalProfilerCsvPath.getText();
  settings.profilerHttpEnabled=radioHttpProfiler.isSelected();
  settings.profilerHttpUrl=textHttpProfilerUrl.getText();
}
