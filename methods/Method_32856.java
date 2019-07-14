void promptNameAndSave(final InputStream in,final String attachmentFileName){
  DialogUtils.textInput(this,R.string.file_received_title,attachmentFileName,R.string.file_received_edit_button,text -> {
    File outFile=saveStreamWithName(in,text);
    if (outFile == null)     return;
    final File editorProgramFile=new File(EDITOR_PROGRAM);
    if (!editorProgramFile.isFile()) {
      showErrorDialogAndQuit("The following file does not exist:\n$HOME/bin/termux-file-editor\n\n" + "Create this file as a script or a symlink - it will be called with the received file as only argument.");
      return;
    }
    editorProgramFile.setExecutable(true);
    final Uri scriptUri=new Uri.Builder().scheme("file").path(EDITOR_PROGRAM).build();
    Intent executeIntent=new Intent(TermuxService.ACTION_EXECUTE,scriptUri);
    executeIntent.setClass(TermuxFileReceiverActivity.this,TermuxService.class);
    executeIntent.putExtra(TermuxService.EXTRA_ARGUMENTS,new String[]{outFile.getAbsolutePath()});
    startService(executeIntent);
    finish();
  }
,R.string.file_received_open_folder_button,text -> {
    if (saveStreamWithName(in,text) == null)     return;
    Intent executeIntent=new Intent(TermuxService.ACTION_EXECUTE);
    executeIntent.putExtra(TermuxService.EXTRA_CURRENT_WORKING_DIRECTORY,TERMUX_RECEIVEDIR);
    executeIntent.setClass(TermuxFileReceiverActivity.this,TermuxService.class);
    startService(executeIntent);
    finish();
  }
,android.R.string.cancel,text -> finish(),dialog -> {
    if (mFinishOnDismissNameDialog)     finish();
  }
);
}
