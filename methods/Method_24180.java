@Override protected void awaitAsyncSaveCompletion(String filename){
  if (asyncPixelReader != null) {
    ongoingPixelTransfersIterable.addAll(ongoingPixelTransfers);
    File file=parent.sketchFile(filename);
    for (    AsyncPixelReader pixelReader : ongoingPixelTransfersIterable) {
      pixelReader.awaitTransferCompletion(file);
    }
    ongoingPixelTransfersIterable.clear();
  }
  super.awaitAsyncSaveCompletion(filename);
}
