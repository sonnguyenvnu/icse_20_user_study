private boolean convertVideo(final MessageObject messageObject){
  if (messageObject == null || messageObject.videoEditedInfo == null) {
    return false;
  }
  String videoPath=messageObject.videoEditedInfo.originalPath;
  long startTime=messageObject.videoEditedInfo.startTime;
  long endTime=messageObject.videoEditedInfo.endTime;
  int resultWidth=messageObject.videoEditedInfo.resultWidth;
  int resultHeight=messageObject.videoEditedInfo.resultHeight;
  int rotationValue=messageObject.videoEditedInfo.rotationValue;
  int originalWidth=messageObject.videoEditedInfo.originalWidth;
  int originalHeight=messageObject.videoEditedInfo.originalHeight;
  int framerate=messageObject.videoEditedInfo.framerate;
  int bitrate=messageObject.videoEditedInfo.bitrate;
  int rotateRender=0;
  boolean isSecret=((int)messageObject.getDialogId()) == 0;
  File cacheFile=new File(messageObject.messageOwner.attachPath);
  if (videoPath == null) {
    videoPath="";
  }
  if (Build.VERSION.SDK_INT < 18 && resultHeight > resultWidth && resultWidth != originalWidth && resultHeight != originalHeight) {
    int temp=resultHeight;
    resultHeight=resultWidth;
    resultWidth=temp;
    rotationValue=90;
    rotateRender=270;
  }
 else   if (Build.VERSION.SDK_INT > 20) {
    if (rotationValue == 90) {
      int temp=resultHeight;
      resultHeight=resultWidth;
      resultWidth=temp;
      rotationValue=0;
      rotateRender=270;
    }
 else     if (rotationValue == 180) {
      rotateRender=180;
      rotationValue=0;
    }
 else     if (rotationValue == 270) {
      int temp=resultHeight;
      resultHeight=resultWidth;
      resultWidth=temp;
      rotationValue=0;
      rotateRender=90;
    }
  }
  SharedPreferences preferences=ApplicationLoader.applicationContext.getSharedPreferences("videoconvert",Activity.MODE_PRIVATE);
  File inputFile=new File(videoPath);
  if (messageObject.getId() != 0) {
    boolean isPreviousOk=preferences.getBoolean("isPreviousOk",true);
    preferences.edit().putBoolean("isPreviousOk",false).commit();
    if (!inputFile.canRead() || !isPreviousOk) {
      didWriteData(messageObject,cacheFile,true,0,true);
      preferences.edit().putBoolean("isPreviousOk",true).commit();
      return false;
    }
  }
  videoConvertFirstWrite=true;
  boolean error=false;
  long time=System.currentTimeMillis();
  if (resultWidth != 0 && resultHeight != 0) {
    MP4Builder mediaMuxer=null;
    MediaExtractor extractor=null;
    try {
      MediaCodec.BufferInfo info=new MediaCodec.BufferInfo();
      Mp4Movie movie=new Mp4Movie();
      movie.setCacheFile(cacheFile);
      movie.setRotation(rotationValue);
      movie.setSize(resultWidth,resultHeight);
      mediaMuxer=new MP4Builder().createMovie(movie,isSecret);
      extractor=new MediaExtractor();
      extractor.setDataSource(videoPath);
      checkConversionCanceled();
      if (resultWidth != originalWidth || resultHeight != originalHeight || rotateRender != 0 || messageObject.videoEditedInfo.roundVideo) {
        int videoIndex=findTrack(extractor,false);
        int audioIndex=bitrate != -1 ? findTrack(extractor,true) : -1;
        if (videoIndex >= 0) {
          MediaCodec decoder=null;
          MediaCodec encoder=null;
          InputSurface inputSurface=null;
          OutputSurface outputSurface=null;
          try {
            long videoTime=-1;
            boolean outputDone=false;
            boolean inputDone=false;
            boolean decoderDone=false;
            int swapUV=0;
            int videoTrackIndex=-5;
            int audioTrackIndex=-5;
            int colorFormat;
            int processorType=PROCESSOR_TYPE_OTHER;
            String manufacturer=Build.MANUFACTURER.toLowerCase();
            if (Build.VERSION.SDK_INT < 18) {
              MediaCodecInfo codecInfo=selectCodec(MIME_TYPE);
              colorFormat=selectColorFormat(codecInfo,MIME_TYPE);
              if (colorFormat == 0) {
                throw new RuntimeException("no supported color format");
              }
              String codecName=codecInfo.getName();
              if (codecName.contains("OMX.qcom.")) {
                processorType=PROCESSOR_TYPE_QCOM;
                if (Build.VERSION.SDK_INT == 16) {
                  if (manufacturer.equals("lge") || manufacturer.equals("nokia")) {
                    swapUV=1;
                  }
                }
              }
 else               if (codecName.contains("OMX.Intel.")) {
                processorType=PROCESSOR_TYPE_INTEL;
              }
 else               if (codecName.equals("OMX.MTK.VIDEO.ENCODER.AVC")) {
                processorType=PROCESSOR_TYPE_MTK;
              }
 else               if (codecName.equals("OMX.SEC.AVC.Encoder")) {
                processorType=PROCESSOR_TYPE_SEC;
                swapUV=1;
              }
 else               if (codecName.equals("OMX.TI.DUCATI1.VIDEO.H264E")) {
                processorType=PROCESSOR_TYPE_TI;
              }
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d("codec = " + codecInfo.getName() + " manufacturer = " + manufacturer + "device = " + Build.MODEL);
              }
            }
 else {
              colorFormat=MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface;
            }
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("colorFormat = " + colorFormat);
            }
            int resultHeightAligned=resultHeight;
            int padding=0;
            int bufferSize=resultWidth * resultHeight * 3 / 2;
            if (processorType == PROCESSOR_TYPE_OTHER) {
              if (resultHeight % 16 != 0) {
                resultHeightAligned+=(16 - (resultHeight % 16));
                padding=resultWidth * (resultHeightAligned - resultHeight);
                bufferSize+=padding * 5 / 4;
              }
            }
 else             if (processorType == PROCESSOR_TYPE_QCOM) {
              if (!manufacturer.toLowerCase().equals("lge")) {
                int uvoffset=(resultWidth * resultHeight + 2047) & ~2047;
                padding=uvoffset - (resultWidth * resultHeight);
                bufferSize+=padding;
              }
            }
 else             if (processorType == PROCESSOR_TYPE_TI) {
            }
 else             if (processorType == PROCESSOR_TYPE_MTK) {
              if (manufacturer.equals("baidu")) {
                resultHeightAligned+=(16 - (resultHeight % 16));
                padding=resultWidth * (resultHeightAligned - resultHeight);
                bufferSize+=padding * 5 / 4;
              }
            }
            extractor.selectTrack(videoIndex);
            MediaFormat videoFormat=extractor.getTrackFormat(videoIndex);
            ByteBuffer audioBuffer=null;
            if (audioIndex >= 0) {
              extractor.selectTrack(audioIndex);
              MediaFormat audioFormat=extractor.getTrackFormat(audioIndex);
              int maxBufferSize=audioFormat.getInteger(MediaFormat.KEY_MAX_INPUT_SIZE);
              audioBuffer=ByteBuffer.allocateDirect(maxBufferSize);
              audioTrackIndex=mediaMuxer.addTrack(audioFormat,true);
            }
            if (startTime > 0) {
              extractor.seekTo(startTime,MediaExtractor.SEEK_TO_PREVIOUS_SYNC);
            }
 else {
              extractor.seekTo(0,MediaExtractor.SEEK_TO_PREVIOUS_SYNC);
            }
            MediaFormat outputFormat=MediaFormat.createVideoFormat(MIME_TYPE,resultWidth,resultHeight);
            outputFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT,colorFormat);
            outputFormat.setInteger(MediaFormat.KEY_BIT_RATE,bitrate > 0 ? bitrate : 921600);
            outputFormat.setInteger(MediaFormat.KEY_FRAME_RATE,framerate != 0 ? framerate : 25);
            outputFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL,2);
            if (Build.VERSION.SDK_INT < 18) {
              outputFormat.setInteger("stride",resultWidth + 32);
              outputFormat.setInteger("slice-height",resultHeight);
            }
            encoder=MediaCodec.createEncoderByType(MIME_TYPE);
            encoder.configure(outputFormat,null,null,MediaCodec.CONFIGURE_FLAG_ENCODE);
            if (Build.VERSION.SDK_INT >= 18) {
              inputSurface=new InputSurface(encoder.createInputSurface());
              inputSurface.makeCurrent();
            }
            encoder.start();
            decoder=MediaCodec.createDecoderByType(videoFormat.getString(MediaFormat.KEY_MIME));
            if (Build.VERSION.SDK_INT >= 18) {
              outputSurface=new OutputSurface();
            }
 else {
              outputSurface=new OutputSurface(resultWidth,resultHeight,rotateRender);
            }
            decoder.configure(videoFormat,outputSurface.getSurface(),null,0);
            decoder.start();
            final int TIMEOUT_USEC=2500;
            ByteBuffer[] decoderInputBuffers=null;
            ByteBuffer[] encoderOutputBuffers=null;
            ByteBuffer[] encoderInputBuffers=null;
            if (Build.VERSION.SDK_INT < 21) {
              decoderInputBuffers=decoder.getInputBuffers();
              encoderOutputBuffers=encoder.getOutputBuffers();
              if (Build.VERSION.SDK_INT < 18) {
                encoderInputBuffers=encoder.getInputBuffers();
              }
            }
            checkConversionCanceled();
            while (!outputDone) {
              checkConversionCanceled();
              if (!inputDone) {
                boolean eof=false;
                int index=extractor.getSampleTrackIndex();
                if (index == videoIndex) {
                  int inputBufIndex=decoder.dequeueInputBuffer(TIMEOUT_USEC);
                  if (inputBufIndex >= 0) {
                    ByteBuffer inputBuf;
                    if (Build.VERSION.SDK_INT < 21) {
                      inputBuf=decoderInputBuffers[inputBufIndex];
                    }
 else {
                      inputBuf=decoder.getInputBuffer(inputBufIndex);
                    }
                    int chunkSize=extractor.readSampleData(inputBuf,0);
                    if (chunkSize < 0) {
                      decoder.queueInputBuffer(inputBufIndex,0,0,0L,MediaCodec.BUFFER_FLAG_END_OF_STREAM);
                      inputDone=true;
                    }
 else {
                      decoder.queueInputBuffer(inputBufIndex,0,chunkSize,extractor.getSampleTime(),0);
                      extractor.advance();
                    }
                  }
                }
 else                 if (audioIndex != -1 && index == audioIndex) {
                  info.size=extractor.readSampleData(audioBuffer,0);
                  if (Build.VERSION.SDK_INT < 21) {
                    audioBuffer.position(0);
                    audioBuffer.limit(info.size);
                  }
                  if (info.size >= 0) {
                    info.presentationTimeUs=extractor.getSampleTime();
                    extractor.advance();
                  }
 else {
                    info.size=0;
                    inputDone=true;
                  }
                  if (info.size > 0 && (endTime < 0 || info.presentationTimeUs < endTime)) {
                    info.offset=0;
                    info.flags=extractor.getSampleFlags();
                    long availableSize=mediaMuxer.writeSampleData(audioTrackIndex,audioBuffer,info,false);
                    if (availableSize != 0) {
                      didWriteData(messageObject,cacheFile,false,availableSize,false);
                    }
                  }
                }
 else                 if (index == -1) {
                  eof=true;
                }
                if (eof) {
                  int inputBufIndex=decoder.dequeueInputBuffer(TIMEOUT_USEC);
                  if (inputBufIndex >= 0) {
                    decoder.queueInputBuffer(inputBufIndex,0,0,0L,MediaCodec.BUFFER_FLAG_END_OF_STREAM);
                    inputDone=true;
                  }
                }
              }
              boolean decoderOutputAvailable=!decoderDone;
              boolean encoderOutputAvailable=true;
              while (decoderOutputAvailable || encoderOutputAvailable) {
                checkConversionCanceled();
                int encoderStatus=encoder.dequeueOutputBuffer(info,TIMEOUT_USEC);
                if (encoderStatus == MediaCodec.INFO_TRY_AGAIN_LATER) {
                  encoderOutputAvailable=false;
                }
 else                 if (encoderStatus == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) {
                  if (Build.VERSION.SDK_INT < 21) {
                    encoderOutputBuffers=encoder.getOutputBuffers();
                  }
                }
 else                 if (encoderStatus == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                  MediaFormat newFormat=encoder.getOutputFormat();
                  if (videoTrackIndex == -5) {
                    videoTrackIndex=mediaMuxer.addTrack(newFormat,false);
                  }
                }
 else                 if (encoderStatus < 0) {
                  throw new RuntimeException("unexpected result from encoder.dequeueOutputBuffer: " + encoderStatus);
                }
 else {
                  ByteBuffer encodedData;
                  if (Build.VERSION.SDK_INT < 21) {
                    encodedData=encoderOutputBuffers[encoderStatus];
                  }
 else {
                    encodedData=encoder.getOutputBuffer(encoderStatus);
                  }
                  if (encodedData == null) {
                    throw new RuntimeException("encoderOutputBuffer " + encoderStatus + " was null");
                  }
                  if (info.size > 1) {
                    if ((info.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) == 0) {
                      long availableSize=mediaMuxer.writeSampleData(videoTrackIndex,encodedData,info,true);
                      if (availableSize != 0) {
                        didWriteData(messageObject,cacheFile,false,availableSize,false);
                      }
                    }
 else                     if (videoTrackIndex == -5) {
                      byte[] csd=new byte[info.size];
                      encodedData.limit(info.offset + info.size);
                      encodedData.position(info.offset);
                      encodedData.get(csd);
                      ByteBuffer sps=null;
                      ByteBuffer pps=null;
                      for (int a=info.size - 1; a >= 0; a--) {
                        if (a > 3) {
                          if (csd[a] == 1 && csd[a - 1] == 0 && csd[a - 2] == 0 && csd[a - 3] == 0) {
                            sps=ByteBuffer.allocate(a - 3);
                            pps=ByteBuffer.allocate(info.size - (a - 3));
                            sps.put(csd,0,a - 3).position(0);
                            pps.put(csd,a - 3,info.size - (a - 3)).position(0);
                            break;
                          }
                        }
 else {
                          break;
                        }
                      }
                      MediaFormat newFormat=MediaFormat.createVideoFormat(MIME_TYPE,resultWidth,resultHeight);
                      if (sps != null && pps != null) {
                        newFormat.setByteBuffer("csd-0",sps);
                        newFormat.setByteBuffer("csd-1",pps);
                      }
                      videoTrackIndex=mediaMuxer.addTrack(newFormat,false);
                    }
                  }
                  outputDone=(info.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0;
                  encoder.releaseOutputBuffer(encoderStatus,false);
                }
                if (encoderStatus != MediaCodec.INFO_TRY_AGAIN_LATER) {
                  continue;
                }
                if (!decoderDone) {
                  int decoderStatus=decoder.dequeueOutputBuffer(info,TIMEOUT_USEC);
                  if (decoderStatus == MediaCodec.INFO_TRY_AGAIN_LATER) {
                    decoderOutputAvailable=false;
                  }
 else                   if (decoderStatus == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) {
                  }
 else                   if (decoderStatus == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                    MediaFormat newFormat=decoder.getOutputFormat();
                    if (BuildVars.LOGS_ENABLED) {
                      FileLog.d("newFormat = " + newFormat);
                    }
                  }
 else                   if (decoderStatus < 0) {
                    throw new RuntimeException("unexpected result from decoder.dequeueOutputBuffer: " + decoderStatus);
                  }
 else {
                    boolean doRender;
                    if (Build.VERSION.SDK_INT >= 18) {
                      doRender=info.size != 0;
                    }
 else {
                      doRender=info.size != 0 || info.presentationTimeUs != 0;
                    }
                    if (endTime > 0 && info.presentationTimeUs >= endTime) {
                      inputDone=true;
                      decoderDone=true;
                      doRender=false;
                      info.flags|=MediaCodec.BUFFER_FLAG_END_OF_STREAM;
                    }
                    if (startTime > 0 && videoTime == -1) {
                      if (info.presentationTimeUs < startTime) {
                        doRender=false;
                        if (BuildVars.LOGS_ENABLED) {
                          FileLog.d("drop frame startTime = " + startTime + " present time = " + info.presentationTimeUs);
                        }
                      }
 else {
                        videoTime=info.presentationTimeUs;
                      }
                    }
                    decoder.releaseOutputBuffer(decoderStatus,doRender);
                    if (doRender) {
                      boolean errorWait=false;
                      try {
                        outputSurface.awaitNewImage();
                      }
 catch (                      Exception e) {
                        errorWait=true;
                        FileLog.e(e);
                      }
                      if (!errorWait) {
                        if (Build.VERSION.SDK_INT >= 18) {
                          outputSurface.drawImage(false);
                          inputSurface.setPresentationTime(info.presentationTimeUs * 1000);
                          inputSurface.swapBuffers();
                        }
 else {
                          int inputBufIndex=encoder.dequeueInputBuffer(TIMEOUT_USEC);
                          if (inputBufIndex >= 0) {
                            outputSurface.drawImage(true);
                            ByteBuffer rgbBuf=outputSurface.getFrame();
                            ByteBuffer yuvBuf=encoderInputBuffers[inputBufIndex];
                            yuvBuf.clear();
                            Utilities.convertVideoFrame(rgbBuf,yuvBuf,colorFormat,resultWidth,resultHeight,padding,swapUV);
                            encoder.queueInputBuffer(inputBufIndex,0,bufferSize,info.presentationTimeUs,0);
                          }
 else {
                            if (BuildVars.LOGS_ENABLED) {
                              FileLog.d("input buffer not available");
                            }
                          }
                        }
                      }
                    }
                    if ((info.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                      decoderOutputAvailable=false;
                      if (BuildVars.LOGS_ENABLED) {
                        FileLog.d("decoder stream end");
                      }
                      if (Build.VERSION.SDK_INT >= 18) {
                        encoder.signalEndOfInputStream();
                      }
 else {
                        int inputBufIndex=encoder.dequeueInputBuffer(TIMEOUT_USEC);
                        if (inputBufIndex >= 0) {
                          encoder.queueInputBuffer(inputBufIndex,0,1,info.presentationTimeUs,MediaCodec.BUFFER_FLAG_END_OF_STREAM);
                        }
                      }
                    }
                  }
                }
              }
            }
          }
 catch (          Exception e) {
            FileLog.e(e);
            error=true;
          }
          extractor.unselectTrack(videoIndex);
          if (outputSurface != null) {
            outputSurface.release();
          }
          if (inputSurface != null) {
            inputSurface.release();
          }
          if (decoder != null) {
            decoder.stop();
            decoder.release();
          }
          if (encoder != null) {
            encoder.stop();
            encoder.release();
          }
          checkConversionCanceled();
        }
      }
 else {
        readAndWriteTracks(messageObject,extractor,mediaMuxer,info,startTime,endTime,cacheFile,bitrate != -1);
      }
    }
 catch (    Exception e) {
      error=true;
      FileLog.e(e);
    }
 finally {
      if (extractor != null) {
        extractor.release();
      }
      if (mediaMuxer != null) {
        try {
          mediaMuxer.finishMovie();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("time = " + (System.currentTimeMillis() - time));
      }
    }
  }
 else {
    preferences.edit().putBoolean("isPreviousOk",true).commit();
    didWriteData(messageObject,cacheFile,true,0,true);
    return false;
  }
  preferences.edit().putBoolean("isPreviousOk",true).commit();
  didWriteData(messageObject,cacheFile,true,0,error);
  return true;
}
