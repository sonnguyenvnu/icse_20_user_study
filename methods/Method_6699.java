private long readAndWriteTracks(final MessageObject messageObject,MediaExtractor extractor,MP4Builder mediaMuxer,MediaCodec.BufferInfo info,long start,long end,File file,boolean needAudio) throws Exception {
  int videoTrackIndex=findTrack(extractor,false);
  int audioTrackIndex=needAudio ? findTrack(extractor,true) : -1;
  int muxerVideoTrackIndex=-1;
  int muxerAudioTrackIndex=-1;
  boolean inputDone=false;
  int maxBufferSize=0;
  if (videoTrackIndex >= 0) {
    extractor.selectTrack(videoTrackIndex);
    MediaFormat trackFormat=extractor.getTrackFormat(videoTrackIndex);
    muxerVideoTrackIndex=mediaMuxer.addTrack(trackFormat,false);
    maxBufferSize=trackFormat.getInteger(MediaFormat.KEY_MAX_INPUT_SIZE);
    if (start > 0) {
      extractor.seekTo(start,MediaExtractor.SEEK_TO_PREVIOUS_SYNC);
    }
 else {
      extractor.seekTo(0,MediaExtractor.SEEK_TO_PREVIOUS_SYNC);
    }
  }
  if (audioTrackIndex >= 0) {
    extractor.selectTrack(audioTrackIndex);
    MediaFormat trackFormat=extractor.getTrackFormat(audioTrackIndex);
    muxerAudioTrackIndex=mediaMuxer.addTrack(trackFormat,true);
    maxBufferSize=Math.max(trackFormat.getInteger(MediaFormat.KEY_MAX_INPUT_SIZE),maxBufferSize);
    if (start > 0) {
      extractor.seekTo(start,MediaExtractor.SEEK_TO_PREVIOUS_SYNC);
    }
 else {
      extractor.seekTo(0,MediaExtractor.SEEK_TO_PREVIOUS_SYNC);
    }
  }
  ByteBuffer buffer=ByteBuffer.allocateDirect(maxBufferSize);
  if (audioTrackIndex >= 0 || videoTrackIndex >= 0) {
    long startTime=-1;
    checkConversionCanceled();
    while (!inputDone) {
      checkConversionCanceled();
      boolean eof=false;
      int muxerTrackIndex;
      info.size=extractor.readSampleData(buffer,0);
      int index=extractor.getSampleTrackIndex();
      if (index == videoTrackIndex) {
        muxerTrackIndex=muxerVideoTrackIndex;
      }
 else       if (index == audioTrackIndex) {
        muxerTrackIndex=muxerAudioTrackIndex;
      }
 else {
        muxerTrackIndex=-1;
      }
      if (muxerTrackIndex != -1) {
        if (Build.VERSION.SDK_INT < 21) {
          buffer.position(0);
          buffer.limit(info.size);
        }
        if (index != audioTrackIndex) {
          byte[] array=buffer.array();
          if (array != null) {
            int offset=buffer.arrayOffset();
            int len=offset + buffer.limit();
            int writeStart=-1;
            for (int a=offset; a <= len - 4; a++) {
              if (array[a] == 0 && array[a + 1] == 0 && array[a + 2] == 0 && array[a + 3] == 1 || a == len - 4) {
                if (writeStart != -1) {
                  int l=a - writeStart - (a != len - 4 ? 4 : 0);
                  array[writeStart]=(byte)(l >> 24);
                  array[writeStart + 1]=(byte)(l >> 16);
                  array[writeStart + 2]=(byte)(l >> 8);
                  array[writeStart + 3]=(byte)l;
                  writeStart=a;
                }
 else {
                  writeStart=a;
                }
              }
            }
          }
        }
        if (info.size >= 0) {
          info.presentationTimeUs=extractor.getSampleTime();
        }
 else {
          info.size=0;
          eof=true;
        }
        if (info.size > 0 && !eof) {
          if (index == videoTrackIndex && start > 0 && startTime == -1) {
            startTime=info.presentationTimeUs;
          }
          if (end < 0 || info.presentationTimeUs < end) {
            info.offset=0;
            info.flags=extractor.getSampleFlags();
            long availableSize=mediaMuxer.writeSampleData(muxerTrackIndex,buffer,info,false);
            if (availableSize != 0) {
              didWriteData(messageObject,file,false,availableSize,false);
            }
          }
 else {
            eof=true;
          }
        }
        if (!eof) {
          extractor.advance();
        }
      }
 else       if (index == -1) {
        eof=true;
      }
 else {
        extractor.advance();
      }
      if (eof) {
        inputDone=true;
      }
    }
    if (videoTrackIndex >= 0) {
      extractor.unselectTrack(videoTrackIndex);
    }
    if (audioTrackIndex >= 0) {
      extractor.unselectTrack(audioTrackIndex);
    }
    return startTime;
  }
  return -1;
}
