private static VideoEditedInfo createCompressionSettings(String videoPath){
  int[] params=new int[AnimatedFileDrawable.PARAM_NUM_COUNT];
  AnimatedFileDrawable.getVideoInfo(videoPath,params);
  if (params[AnimatedFileDrawable.PARAM_NUM_IS_AVC] == 0) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("video hasn't avc1 atom");
    }
    return null;
  }
  int originalBitrate=params[AnimatedFileDrawable.PARAM_NUM_BITRATE];
  int bitrate=params[AnimatedFileDrawable.PARAM_NUM_BITRATE];
  float videoDuration=params[AnimatedFileDrawable.PARAM_NUM_DURATION];
  long videoFramesSize=params[AnimatedFileDrawable.PARAM_NUM_VIDEO_FRAME_SIZE];
  long audioFramesSize=params[AnimatedFileDrawable.PARAM_NUM_AUDIO_FRAME_SIZE];
  int videoFramerate=params[AnimatedFileDrawable.PARAM_NUM_FRAMERATE];
  if (bitrate > 900000) {
    bitrate=900000;
  }
  if (Build.VERSION.SDK_INT < 18) {
    try {
      MediaCodecInfo codecInfo=MediaController.selectCodec(MediaController.MIME_TYPE);
      if (codecInfo == null) {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("no codec info for " + MediaController.MIME_TYPE);
        }
        return null;
      }
 else {
        String name=codecInfo.getName();
        if (name.equals("OMX.google.h264.encoder") || name.equals("OMX.ST.VFM.H264Enc") || name.equals("OMX.Exynos.avc.enc") || name.equals("OMX.MARVELL.VIDEO.HW.CODA7542ENCODER") || name.equals("OMX.MARVELL.VIDEO.H264ENCODER") || name.equals("OMX.k3.video.encoder.avc") || name.equals("OMX.TI.DUCATI1.VIDEO.H264E")) {
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("unsupported encoder = " + name);
          }
          return null;
        }
 else {
          if (MediaController.selectColorFormat(codecInfo,MediaController.MIME_TYPE) == 0) {
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("no color format for " + MediaController.MIME_TYPE);
            }
            return null;
          }
        }
      }
    }
 catch (    Exception e) {
      return null;
    }
  }
  VideoEditedInfo videoEditedInfo=new VideoEditedInfo();
  videoEditedInfo.startTime=-1;
  videoEditedInfo.endTime=-1;
  videoEditedInfo.bitrate=bitrate;
  videoEditedInfo.originalPath=videoPath;
  videoEditedInfo.framerate=videoFramerate;
  videoEditedInfo.estimatedDuration=(long)Math.ceil(videoDuration);
  videoEditedInfo.resultWidth=videoEditedInfo.originalWidth=params[AnimatedFileDrawable.PARAM_NUM_WIDTH];
  videoEditedInfo.resultHeight=videoEditedInfo.originalHeight=params[AnimatedFileDrawable.PARAM_NUM_HEIGHT];
  videoEditedInfo.rotationValue=params[AnimatedFileDrawable.PARAM_NUM_ROTATION];
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  int selectedCompression=preferences.getInt("compress_video2",1);
  int compressionsCount;
  if (videoEditedInfo.originalWidth > 1280 || videoEditedInfo.originalHeight > 1280) {
    compressionsCount=5;
  }
 else   if (videoEditedInfo.originalWidth > 848 || videoEditedInfo.originalHeight > 848) {
    compressionsCount=4;
  }
 else   if (videoEditedInfo.originalWidth > 640 || videoEditedInfo.originalHeight > 640) {
    compressionsCount=3;
  }
 else   if (videoEditedInfo.originalWidth > 480 || videoEditedInfo.originalHeight > 480) {
    compressionsCount=2;
  }
 else {
    compressionsCount=1;
  }
  if (selectedCompression >= compressionsCount) {
    selectedCompression=compressionsCount - 1;
  }
  if (selectedCompression != compressionsCount - 1) {
    float maxSize;
    int targetBitrate;
switch (selectedCompression) {
case 0:
      maxSize=432.0f;
    targetBitrate=400000;
  break;
case 1:
maxSize=640.0f;
targetBitrate=900000;
break;
case 2:
maxSize=848.0f;
targetBitrate=1100000;
break;
case 3:
default :
targetBitrate=2500000;
maxSize=1280.0f;
break;
}
float scale=videoEditedInfo.originalWidth > videoEditedInfo.originalHeight ? maxSize / videoEditedInfo.originalWidth : maxSize / videoEditedInfo.originalHeight;
videoEditedInfo.resultWidth=Math.round(videoEditedInfo.originalWidth * scale / 2) * 2;
videoEditedInfo.resultHeight=Math.round(videoEditedInfo.originalHeight * scale / 2) * 2;
if (bitrate != 0) {
bitrate=Math.min(targetBitrate,(int)(originalBitrate / scale));
videoFramesSize=(long)(bitrate / 8 * videoDuration / 1000);
}
}
if (selectedCompression == compressionsCount - 1) {
videoEditedInfo.resultWidth=videoEditedInfo.originalWidth;
videoEditedInfo.resultHeight=videoEditedInfo.originalHeight;
videoEditedInfo.bitrate=originalBitrate;
videoEditedInfo.estimatedSize=(int)(new File(videoPath).length());
}
 else {
videoEditedInfo.bitrate=bitrate;
videoEditedInfo.estimatedSize=(int)(audioFramesSize + videoFramesSize);
videoEditedInfo.estimatedSize+=videoEditedInfo.estimatedSize / (32 * 1024) * 16;
}
if (videoEditedInfo.estimatedSize == 0) {
videoEditedInfo.estimatedSize=1;
}
return videoEditedInfo;
}
