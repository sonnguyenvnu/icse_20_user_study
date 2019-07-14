public void processResults(final DatapointParcelable dataPackage,int serviceType){
  if (dataPackage != null) {
    String name=dataPackage.name;
    long total=dataPackage.totalSize;
    long doneBytes=dataPackage.byteProgress;
    boolean move=dataPackage.move;
    if (!isInitialized) {
      chartInit(total);
      setupDrawables(serviceType,move);
      isInitialized=true;
    }
    addEntry(FileUtils.readableFileSizeFloat(doneBytes),FileUtils.readableFileSizeFloat(dataPackage.speedRaw));
    mProgressFileNameText.setText(name);
    Spanned bytesText=Html.fromHtml(getResources().getString(R.string.written) + " <font color='" + accentColor + "'><i>" + Formatter.formatFileSize(getContext(),doneBytes) + " </font></i>" + getResources().getString(R.string.out_of) + " <i>" + Formatter.formatFileSize(getContext(),total) + "</i>");
    mProgressBytesText.setText(bytesText);
    Spanned fileProcessedSpan=Html.fromHtml(getResources().getString(R.string.processing_file) + " <font color='" + accentColor + "'><i>" + (dataPackage.sourceProgress) + " </font></i>" + getResources().getString(R.string.of) + " <i>" + dataPackage.sourceFiles + "</i>");
    mProgressFileText.setText(fileProcessedSpan);
    Spanned speedSpan=Html.fromHtml(getResources().getString(R.string.current_speed) + ": <font color='" + accentColor + "'><i>" + Formatter.formatFileSize(getContext(),dataPackage.speedRaw) + "/s</font></i>");
    mProgressSpeedText.setText(speedSpan);
    Spanned timerSpan=Html.fromHtml(getResources().getString(R.string.service_timer) + ": <font color='" + accentColor + "'><i>" + Utils.formatTimer(++looseTimeInSeconds) + "</font></i>");
    mProgressTimer.setText(timerSpan);
    if (dataPackage.completed)     mCancelButton.setVisibility(View.GONE);
  }
}
