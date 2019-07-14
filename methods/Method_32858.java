void handleUrlAndFinish(final String url){
  final File urlOpenerProgramFile=new File(URL_OPENER_PROGRAM);
  if (!urlOpenerProgramFile.isFile()) {
    showErrorDialogAndQuit("The following file does not exist:\n$HOME/bin/termux-url-opener\n\n" + "Create this file as a script or a symlink - it will be called with the shared URL as only argument.");
    return;
  }
  urlOpenerProgramFile.setExecutable(true);
  final Uri urlOpenerProgramUri=new Uri.Builder().scheme("file").path(URL_OPENER_PROGRAM).build();
  Intent executeIntent=new Intent(TermuxService.ACTION_EXECUTE,urlOpenerProgramUri);
  executeIntent.setClass(TermuxFileReceiverActivity.this,TermuxService.class);
  executeIntent.putExtra(TermuxService.EXTRA_ARGUMENTS,new String[]{url});
  startService(executeIntent);
  finish();
}
