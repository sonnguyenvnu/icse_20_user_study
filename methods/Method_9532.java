private void processOpenVideo(final String videoPath,boolean muted){
  if (currentLoadingVideoRunnable != null) {
    Utilities.globalQueue.cancelRunnable(currentLoadingVideoRunnable);
    currentLoadingVideoRunnable=null;
  }
  videoPreviewMessageObject=null;
  setCompressItemEnabled(false,true);
  muteVideo=muted;
  videoTimelineView.setVideoPath(videoPath);
  compressionsCount=-1;
  rotationValue=0;
  videoFramerate=25;
  File file=new File(videoPath);
  originalSize=file.length();
  Utilities.globalQueue.postRunnable(currentLoadingVideoRunnable=new Runnable(){
    @Override public void run(){
      if (currentLoadingVideoRunnable != this) {
        return;
      }
      int[] params=new int[AnimatedFileDrawable.PARAM_NUM_COUNT];
      AnimatedFileDrawable.getVideoInfo(videoPath,params);
      if (currentLoadingVideoRunnable != this) {
        return;
      }
      currentLoadingVideoRunnable=null;
      AndroidUtilities.runOnUIThread(() -> {
        if (parentActivity == null) {
          return;
        }
        videoHasAudio=params[AnimatedFileDrawable.PARAM_NUM_IS_AVC] != 0;
        audioFramesSize=params[AnimatedFileDrawable.PARAM_NUM_AUDIO_FRAME_SIZE];
        videoFramesSize=params[AnimatedFileDrawable.PARAM_NUM_VIDEO_FRAME_SIZE];
        videoDuration=params[AnimatedFileDrawable.PARAM_NUM_DURATION];
        originalBitrate=bitrate=params[AnimatedFileDrawable.PARAM_NUM_BITRATE];
        videoFramerate=params[AnimatedFileDrawable.PARAM_NUM_FRAMERATE];
        if (bitrate > 900000) {
          bitrate=900000;
        }
        if (videoHasAudio) {
          rotationValue=params[AnimatedFileDrawable.PARAM_NUM_ROTATION];
          resultWidth=originalWidth=params[AnimatedFileDrawable.PARAM_NUM_WIDTH];
          resultHeight=originalHeight=params[AnimatedFileDrawable.PARAM_NUM_HEIGHT];
          SharedPreferences preferences=MessagesController.getGlobalMainSettings();
          selectedCompression=preferences.getInt("compress_video2",1);
          if (originalWidth > 1280 || originalHeight > 1280) {
            compressionsCount=5;
          }
 else           if (originalWidth > 854 || originalHeight > 854) {
            compressionsCount=4;
          }
 else           if (originalWidth > 640 || originalHeight > 640) {
            compressionsCount=3;
          }
 else           if (originalWidth > 480 || originalHeight > 480) {
            compressionsCount=2;
          }
 else {
            compressionsCount=1;
          }
          updateWidthHeightBitrateForCompression();
          setCompressItemEnabled(compressionsCount > 1,true);
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("compressionsCount = " + compressionsCount + " w = " + originalWidth + " h = " + originalHeight);
          }
          if (Build.VERSION.SDK_INT < 18 && compressItem.getTag() != null) {
            try {
              MediaCodecInfo codecInfo=MediaController.selectCodec(MediaController.MIME_TYPE);
              if (codecInfo == null) {
                if (BuildVars.LOGS_ENABLED) {
                  FileLog.d("no codec info for " + MediaController.MIME_TYPE);
                }
                setCompressItemEnabled(false,true);
              }
 else {
                String name=codecInfo.getName();
                if (name.equals("OMX.google.h264.encoder") || name.equals("OMX.ST.VFM.H264Enc") || name.equals("OMX.Exynos.avc.enc") || name.equals("OMX.MARVELL.VIDEO.HW.CODA7542ENCODER") || name.equals("OMX.MARVELL.VIDEO.H264ENCODER") || name.equals("OMX.k3.video.encoder.avc") || name.equals("OMX.TI.DUCATI1.VIDEO.H264E")) {
                  if (BuildVars.LOGS_ENABLED) {
                    FileLog.d("unsupported encoder = " + name);
                  }
                  setCompressItemEnabled(false,true);
                }
 else {
                  if (MediaController.selectColorFormat(codecInfo,MediaController.MIME_TYPE) == 0) {
                    if (BuildVars.LOGS_ENABLED) {
                      FileLog.d("no color format for " + MediaController.MIME_TYPE);
                    }
                    setCompressItemEnabled(false,true);
                  }
                }
              }
            }
 catch (            Exception e) {
              setCompressItemEnabled(false,true);
              FileLog.e(e);
            }
          }
          qualityChooseView.invalidate();
        }
 else {
          compressionsCount=0;
        }
        updateVideoInfo();
        updateMuteButton();
      }
);
    }
  }
);
}
