private void calcTotalPartsCount(){
  if (uploadFirstPartLater) {
    if (isBigFile) {
      totalPartsCount=1 + (int)((totalFileSize - uploadChunkSize) + uploadChunkSize - 1) / uploadChunkSize;
    }
 else {
      totalPartsCount=1 + (int)((totalFileSize - 1024) + uploadChunkSize - 1) / uploadChunkSize;
    }
  }
 else {
    totalPartsCount=(int)(totalFileSize + uploadChunkSize - 1) / uploadChunkSize;
  }
}
