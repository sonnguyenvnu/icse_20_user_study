public static void run(final PmsConfiguration configuration){
  int numberOfQuestions=Platform.isMac() ? 4 : 5;
  int currentQuestionNumber=1;
  String status=new StringBuilder().append(Messages.getString("Wizard.2")).append(" %d ").append(Messages.getString("Wizard.4")).append(" ").append(numberOfQuestions).toString();
  Object[] okOptions={Messages.getString("Dialog.OK")};
  Object[] yesNoOptions={Messages.getString("Dialog.YES"),Messages.getString("Dialog.NO")};
  Object[] networkTypeOptions={Messages.getString("Wizard.8"),Messages.getString("Wizard.9"),Messages.getString("Wizard.10")};
  if (!Platform.isMac()) {
    int whetherToStartMinimized=JOptionPane.showOptionDialog(null,Messages.getString("Wizard.3"),String.format(status,currentQuestionNumber++),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,yesNoOptions,yesNoOptions[1]);
    if (whetherToStartMinimized == JOptionPane.YES_OPTION) {
      configuration.setMinimized(true);
    }
 else     if (whetherToStartMinimized == JOptionPane.NO_OPTION) {
      configuration.setMinimized(false);
    }
  }
  int networkType=JOptionPane.showOptionDialog(null,Messages.getString("Wizard.7"),String.format(status,currentQuestionNumber++),JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,networkTypeOptions,networkTypeOptions[1]);
switch (networkType) {
case JOptionPane.YES_OPTION:
    configuration.setMaximumBitrate("0");
  configuration.setMPEG2MainSettings("Automatic (Wired)");
configuration.setx264ConstantRateFactor("Automatic (Wired)");
break;
case JOptionPane.NO_OPTION:
configuration.setMaximumBitrate("90");
configuration.setMPEG2MainSettings("Automatic (Wired)");
configuration.setx264ConstantRateFactor("Automatic (Wired)");
break;
case JOptionPane.CANCEL_OPTION:
configuration.setMaximumBitrate("30");
configuration.setMPEG2MainSettings("Automatic (Wireless)");
configuration.setx264ConstantRateFactor("Automatic (Wireless)");
break;
default :
break;
}
int whetherToHideAdvancedOptions=JOptionPane.showOptionDialog(null,Messages.getString("Wizard.11"),String.format(status,currentQuestionNumber++),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,yesNoOptions,yesNoOptions[0]);
if (whetherToHideAdvancedOptions == JOptionPane.YES_OPTION) {
configuration.setHideAdvancedOptions(true);
}
 else if (whetherToHideAdvancedOptions == JOptionPane.NO_OPTION) {
configuration.setHideAdvancedOptions(false);
}
int whetherToScanSharedFolders=JOptionPane.showOptionDialog(null,Messages.getString("Wizard.IsStartupScan"),String.format(status,currentQuestionNumber++),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,yesNoOptions,yesNoOptions[0]);
if (whetherToScanSharedFolders == JOptionPane.YES_OPTION) {
configuration.setScanSharedFoldersOnStartup(true);
}
 else if (whetherToScanSharedFolders == JOptionPane.NO_OPTION) {
configuration.setScanSharedFoldersOnStartup(false);
}
JOptionPane.showOptionDialog(null,Messages.getString("Wizard.12"),String.format(status,currentQuestionNumber++),JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE,null,okOptions,okOptions[0]);
try {
SwingUtilities.invokeAndWait(new Runnable(){
@Override public void run(){
JFileChooser chooser;
try {
chooser=new JFileChooser();
}
 catch (Exception ee) {
chooser=new JFileChooser(new RestrictedFileSystemView());
}
chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
chooser.setDialogTitle(Messages.getString("Wizard.12"));
chooser.setMultiSelectionEnabled(false);
if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
configuration.setOnlySharedDirectory(chooser.getSelectedFile().getAbsolutePath());
}
 else {
}
}
}
);
}
 catch (InterruptedException|InvocationTargetException e) {
LOGGER.error("Error when saving folders: ",e);
}
configuration.setRunWizard(false);
try {
configuration.save();
}
 catch (ConfigurationException e) {
LOGGER.error("Error when saving changed configuration: ",e);
}
}
