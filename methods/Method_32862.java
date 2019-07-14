public String getTranscriptText(){
  return getSelectedText(0,-getActiveTranscriptRows(),mColumns,mScreenRows).trim();
}
