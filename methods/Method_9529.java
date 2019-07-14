private void updateVideoInfo(){
  if (actionBar == null) {
    return;
  }
  if (compressionsCount == 0) {
    actionBar.setSubtitle(null);
    return;
  }
  if (selectedCompression == 0) {
    compressItem.setImageResource(R.drawable.video_240);
  }
 else   if (selectedCompression == 1) {
    compressItem.setImageResource(R.drawable.video_360);
  }
 else   if (selectedCompression == 2) {
    compressItem.setImageResource(R.drawable.video_480);
  }
 else   if (selectedCompression == 3) {
    compressItem.setImageResource(R.drawable.video_720);
  }
 else   if (selectedCompression == 4) {
    compressItem.setImageResource(R.drawable.video_1080);
  }
  String[] compressionStrings={"240","360","480","720","1080"};
  compressItem.setContentDescription(LocaleController.getString("AccDescrVideoQuality",R.string.AccDescrVideoQuality) + ", " + compressionStrings[Math.max(0,selectedCompression)]);
  estimatedDuration=(long)Math.ceil((videoTimelineView.getRightProgress() - videoTimelineView.getLeftProgress()) * videoDuration);
  int width;
  int height;
  if (compressItem.getTag() == null || selectedCompression == compressionsCount - 1) {
    width=rotationValue == 90 || rotationValue == 270 ? originalHeight : originalWidth;
    height=rotationValue == 90 || rotationValue == 270 ? originalWidth : originalHeight;
    estimatedSize=(int)(originalSize * ((float)estimatedDuration / videoDuration));
  }
 else {
    width=rotationValue == 90 || rotationValue == 270 ? resultHeight : resultWidth;
    height=rotationValue == 90 || rotationValue == 270 ? resultWidth : resultHeight;
    estimatedSize=(int)((audioFramesSize + videoFramesSize) * ((float)estimatedDuration / videoDuration));
    estimatedSize+=estimatedSize / (32 * 1024) * 16;
  }
  if (videoTimelineView.getLeftProgress() == 0) {
    startTime=-1;
  }
 else {
    startTime=(long)(videoTimelineView.getLeftProgress() * videoDuration) * 1000;
  }
  if (videoTimelineView.getRightProgress() == 1) {
    endTime=-1;
  }
 else {
    endTime=(long)(videoTimelineView.getRightProgress() * videoDuration) * 1000;
  }
  String videoDimension=String.format("%dx%d",width,height);
  int minutes=(int)(estimatedDuration / 1000 / 60);
  int seconds=(int)Math.ceil(estimatedDuration / 1000) - minutes * 60;
  String videoTimeSize=String.format("%d:%02d, ~%s",minutes,seconds,AndroidUtilities.formatFileSize(estimatedSize));
  currentSubtitle=String.format("%s, %s",videoDimension,videoTimeSize);
  actionBar.setSubtitle(muteVideo ? null : currentSubtitle);
}
