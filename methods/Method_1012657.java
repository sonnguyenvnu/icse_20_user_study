/** 
 * Parse media without using MediaInfo.
 */
public void parse(InputFile inputFile,Format ext,int type,boolean thumbOnly,boolean resume,RendererConfiguration renderer){
  int i=0;
  while (isParsing()) {
    if (i == 5) {
      mediaparsed=true;
      break;
    }
    try {
      Thread.sleep(1000);
    }
 catch (    InterruptedException e) {
    }
    i++;
  }
  if (isMediaparsed() && !thumbOnly) {
    return;
  }
  if (inputFile != null) {
    File file=inputFile.getFile();
    if (file != null) {
      size=file.length();
    }
 else {
      size=inputFile.getSize();
    }
    ProcessWrapperImpl pw=null;
    boolean ffmpeg_parsing=true;
    if (type == Format.AUDIO || ext instanceof AudioAsVideo) {
      ffmpeg_parsing=false;
      DLNAMediaAudio audio=new DLNAMediaAudio();
      if (file != null) {
        try {
          AudioFile af;
          if ("mp2".equals(FileUtil.getExtension(file).toLowerCase(Locale.ROOT))) {
            af=AudioFileIO.readAs(file,"mp3");
          }
 else {
            af=AudioFileIO.read(file);
          }
          AudioHeader ah=af.getAudioHeader();
          if (ah != null && !thumbOnly) {
            int length=ah.getTrackLength();
            int rate=ah.getSampleRateAsNumber();
            if (ah.getEncodingType() != null && ah.getEncodingType().toLowerCase().contains("flac 24")) {
              audio.setBitsperSample(24);
            }
            audio.setSampleFrequency("" + rate);
            durationSec=(double)length;
            bitrate=(int)ah.getBitRateAsNumber();
            audio.getAudioProperties().setNumberOfChannels(2);
            String channels=ah.getChannels().toLowerCase(Locale.ROOT);
            if (StringUtils.isNotBlank(channels)) {
              if (channels.equals("1") || channels.contains("mono")) {
                audio.getAudioProperties().setNumberOfChannels(1);
              }
 else               if (!(channels.equals("2") || channels.equals("0") || channels.contains("stereo"))) {
                try {
                  audio.getAudioProperties().setNumberOfChannels(Integer.parseInt(channels));
                }
 catch (                IllegalArgumentException e) {
                  LOGGER.debug("Could not parse number of audio channels from \"{}\"",channels);
                }
              }
            }
            if (StringUtils.isNotBlank(ah.getEncodingType())) {
              audio.setCodecA(ah.getEncodingType());
            }
            if (audio.getCodecA() != null && audio.getCodecA().contains("(windows media")) {
              audio.setCodecA(audio.getCodecA().substring(0,audio.getCodecA().indexOf("(windows media")).trim());
            }
          }
          Tag t=af.getTag();
          if (t != null) {
            if (t.getArtworkList().size() > 0) {
              thumb=DLNAThumbnail.toThumbnail(t.getArtworkList().get(0).getBinaryData(),640,480,ScaleType.MAX,ImageFormat.SOURCE,false);
            }
 else             if (!configuration.getAudioThumbnailMethod().equals(CoverSupplier.NONE)) {
              thumb=DLNAThumbnail.toThumbnail(CoverUtil.get().getThumbnail(t),640,480,ScaleType.MAX,ImageFormat.SOURCE,false);
            }
            if (thumb != null) {
              thumbready=true;
            }
            if (!thumbOnly) {
              audio.setAlbum(t.getFirst(FieldKey.ALBUM));
              audio.setArtist(t.getFirst(FieldKey.ARTIST));
              audio.setSongname(t.getFirst(FieldKey.TITLE));
              String y=t.getFirst(FieldKey.YEAR);
              try {
                if (y.length() > 4) {
                  y=y.substring(0,4);
                }
                audio.setYear(Integer.parseInt(((y != null && y.length() > 0) ? y : "0")));
                y=t.getFirst(FieldKey.TRACK);
                audio.setTrack(Integer.parseInt(((y != null && y.length() > 0) ? y : "1")));
                audio.setGenre(t.getFirst(FieldKey.GENRE));
              }
 catch (              NumberFormatException|KeyNotFoundException e) {
                LOGGER.debug("Error parsing unimportant metadata: " + e.getMessage());
              }
            }
          }
        }
 catch (        CannotReadException e) {
          if (e.getMessage().startsWith(ErrorMessage.NO_READER_FOR_THIS_FORMAT.getMsg().substring(0,ErrorMessage.NO_READER_FOR_THIS_FORMAT.getMsg().indexOf("{")))) {
            LOGGER.debug("No audio tag support for audio file \"{}\"",file.getName());
          }
 else {
            LOGGER.error("Error reading audio tag for \"{}\": {}",file.getName(),e.getMessage());
            LOGGER.trace("",e);
          }
        }
catch (        IOException|TagException|ReadOnlyFileException|InvalidAudioFrameException|NumberFormatException|KeyNotFoundException e) {
          LOGGER.debug("Error parsing audio file tag for \"{}\": {}",file.getName(),e.getMessage());
          LOGGER.trace("",e);
          ffmpeg_parsing=false;
        }
        if (StringUtils.isBlank(container) && ext != null) {
          if (ext.getIdentifier() == Identifier.ADPCM) {
            audio.setCodecA(FormatConfiguration.ADPCM);
          }
 else           if (ext.getIdentifier() == Identifier.DSF) {
            audio.setCodecA(FormatConfiguration.DSF);
          }
 else           if (ext.getIdentifier() == Identifier.DFF) {
            audio.setCodecA(FormatConfiguration.DFF);
          }
        }
        if (StringUtils.isNotBlank(audio.getSongname())) {
          if (renderer != null && renderer.isPrependTrackNumbers() && audio.getTrack() > 0) {
            audio.setSongname(audio.getTrack() + ": " + audio.getSongname());
          }
        }
 else {
          audio.setSongname(file.getName());
        }
        if (!ffmpeg_parsing) {
          audioTracks.add(audio);
        }
      }
      if (StringUtils.isBlank(container)) {
        container=audio.getCodecA();
      }
    }
    if (type == Format.IMAGE && file != null) {
      if (!thumbOnly) {
        try {
          ffmpeg_parsing=false;
          ImagesUtil.parseImage(file,this);
          imageCount++;
        }
 catch (        IOException e) {
          LOGGER.debug("Error parsing image \"{}\", switching to FFmpeg: {}",file.getAbsolutePath(),e.getMessage());
          LOGGER.trace("",e);
          ffmpeg_parsing=true;
        }
      }
      if (thumbOnly && configuration.isThumbnailGenerationEnabled() && configuration.getImageThumbnailsEnabled()) {
        LOGGER.trace("Creating thumbnail for \"{}\"",file.getName());
        try {
          if (imageInfo instanceof ExifInfo && ((ExifInfo)imageInfo).hasExifThumbnail() && !imageInfo.isImageIOSupported()) {
          }
 else {
            thumb=DLNAThumbnail.toThumbnail(Files.newInputStream(file.toPath()),320,320,ScaleType.MAX,ImageFormat.SOURCE,false);
          }
          thumbready=true;
        }
 catch (        EOFException e) {
          LOGGER.debug("Error generating thumbnail for \"{}\": Unexpected end of file, probably corrupt file or read error.",file.getName());
        }
catch (        UnknownFormatException e) {
          LOGGER.debug("Could not generate thumbnail for \"{}\" because the format is unknown: {}",file.getName(),e.getMessage());
        }
catch (        IOException e) {
          LOGGER.debug("Error generating thumbnail for \"{}\": {}",file.getName(),e.getMessage());
          LOGGER.trace("",e);
        }
      }
    }
    if (ffmpeg_parsing) {
      if (!thumbOnly || (type == Format.VIDEO && !configuration.isUseMplayerForVideoThumbs())) {
        pw=getFFmpegThumbnail(inputFile,resume);
      }
      String input="-";
      if (file != null) {
        input=ProcessUtil.getShortFileNameIfWideChars(file.getAbsolutePath());
      }
synchronized (ffmpeg_failureLock) {
        if (pw != null && !ffmpeg_failure && !thumbOnly) {
          parseFFmpegInfo(pw.getResults(),input);
        }
      }
      if (!thumbOnly && container != null && file != null && container.equals("mpegts") && isH264() && getDurationInSeconds() == 0) {
        try {
          int length=MpegUtil.getDurationFromMpeg(file);
          if (length > 0) {
            durationSec=(double)length;
          }
        }
 catch (        IOException e) {
          LOGGER.trace("Error retrieving length: " + e.getMessage());
        }
      }
      if (configuration.isUseMplayerForVideoThumbs() && type == Format.VIDEO) {
        try {
          getMplayerThumbnail(inputFile,resume);
          String frameName="" + inputFile.hashCode();
          frameName=configuration.getTempFolder() + "/mplayer_thumbs/" + frameName + "00000001/00000001.jpg";
          frameName=frameName.replace(',','_');
          File jpg=new File(frameName);
          if (jpg.exists()) {
            try (InputStream is=new FileInputStream(jpg)){
              int sz=is.available();
              if (sz > 0) {
                byte[] bytes=new byte[sz];
                is.read(bytes);
                thumb=DLNAThumbnail.toThumbnail(bytes,640,480,ScaleType.MAX,ImageFormat.SOURCE,false);
                thumbready=true;
              }
            }
             if (!jpg.delete()) {
              jpg.deleteOnExit();
            }
            if (!jpg.getParentFile().delete() && !jpg.getParentFile().delete()) {
              LOGGER.debug("Failed to delete \"" + jpg.getParentFile().getAbsolutePath() + "\"");
            }
          }
        }
 catch (        IOException e) {
          LOGGER.debug("Caught exception",e);
        }
      }
      if (type == Format.VIDEO && pw != null && thumb == null && pw.getOutputByteArray() != null) {
        byte[] bytes=pw.getOutputByteArray().toByteArray();
        if (bytes != null && bytes.length > 0) {
          try {
            thumb=DLNAThumbnail.toThumbnail(bytes);
          }
 catch (          IOException e) {
            LOGGER.debug("Error while decoding thumbnail: " + e.getMessage());
            LOGGER.trace("",e);
          }
          thumbready=true;
        }
      }
    }
    postParse(type,inputFile);
    mediaparsed=true;
  }
}
