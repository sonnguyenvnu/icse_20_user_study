/** 
 * Parses an stbl atom (defined in 14496-12).
 * @param track Track to which this sample table corresponds.
 * @param stblAtom stbl (sample table) atom to decode.
 * @param gaplessInfoHolder Holder to populate with gapless playback information.
 * @return Sample table described by the stbl atom.
 * @throws ParserException Thrown if the stbl atom can't be parsed.
 */
public static TrackSampleTable parseStbl(Track track,Atom.ContainerAtom stblAtom,GaplessInfoHolder gaplessInfoHolder) throws ParserException {
  SampleSizeBox sampleSizeBox;
  Atom.LeafAtom stszAtom=stblAtom.getLeafAtomOfType(Atom.TYPE_stsz);
  if (stszAtom != null) {
    sampleSizeBox=new StszSampleSizeBox(stszAtom);
  }
 else {
    Atom.LeafAtom stz2Atom=stblAtom.getLeafAtomOfType(Atom.TYPE_stz2);
    if (stz2Atom == null) {
      throw new ParserException("Track has no sample table size information");
    }
    sampleSizeBox=new Stz2SampleSizeBox(stz2Atom);
  }
  int sampleCount=sampleSizeBox.getSampleCount();
  if (sampleCount == 0) {
    return new TrackSampleTable(track,new long[0],new int[0],0,new long[0],new int[0],C.TIME_UNSET);
  }
  boolean chunkOffsetsAreLongs=false;
  Atom.LeafAtom chunkOffsetsAtom=stblAtom.getLeafAtomOfType(Atom.TYPE_stco);
  if (chunkOffsetsAtom == null) {
    chunkOffsetsAreLongs=true;
    chunkOffsetsAtom=stblAtom.getLeafAtomOfType(Atom.TYPE_co64);
  }
  ParsableByteArray chunkOffsets=chunkOffsetsAtom.data;
  ParsableByteArray stsc=stblAtom.getLeafAtomOfType(Atom.TYPE_stsc).data;
  ParsableByteArray stts=stblAtom.getLeafAtomOfType(Atom.TYPE_stts).data;
  Atom.LeafAtom stssAtom=stblAtom.getLeafAtomOfType(Atom.TYPE_stss);
  ParsableByteArray stss=stssAtom != null ? stssAtom.data : null;
  Atom.LeafAtom cttsAtom=stblAtom.getLeafAtomOfType(Atom.TYPE_ctts);
  ParsableByteArray ctts=cttsAtom != null ? cttsAtom.data : null;
  ChunkIterator chunkIterator=new ChunkIterator(stsc,chunkOffsets,chunkOffsetsAreLongs);
  stts.setPosition(Atom.FULL_HEADER_SIZE);
  int remainingTimestampDeltaChanges=stts.readUnsignedIntToInt() - 1;
  int remainingSamplesAtTimestampDelta=stts.readUnsignedIntToInt();
  int timestampDeltaInTimeUnits=stts.readUnsignedIntToInt();
  int remainingSamplesAtTimestampOffset=0;
  int remainingTimestampOffsetChanges=0;
  int timestampOffset=0;
  if (ctts != null) {
    ctts.setPosition(Atom.FULL_HEADER_SIZE);
    remainingTimestampOffsetChanges=ctts.readUnsignedIntToInt();
  }
  int nextSynchronizationSampleIndex=C.INDEX_UNSET;
  int remainingSynchronizationSamples=0;
  if (stss != null) {
    stss.setPosition(Atom.FULL_HEADER_SIZE);
    remainingSynchronizationSamples=stss.readUnsignedIntToInt();
    if (remainingSynchronizationSamples > 0) {
      nextSynchronizationSampleIndex=stss.readUnsignedIntToInt() - 1;
    }
 else {
      stss=null;
    }
  }
  boolean isFixedSampleSizeRawAudio=sampleSizeBox.isFixedSampleSize() && MimeTypes.AUDIO_RAW.equals(track.format.sampleMimeType) && remainingTimestampDeltaChanges == 0 && remainingTimestampOffsetChanges == 0 && remainingSynchronizationSamples == 0;
  long[] offsets;
  int[] sizes;
  int maximumSize=0;
  long[] timestamps;
  int[] flags;
  long timestampTimeUnits=0;
  long duration;
  if (!isFixedSampleSizeRawAudio) {
    offsets=new long[sampleCount];
    sizes=new int[sampleCount];
    timestamps=new long[sampleCount];
    flags=new int[sampleCount];
    long offset=0;
    int remainingSamplesInChunk=0;
    for (int i=0; i < sampleCount; i++) {
      boolean chunkDataComplete=true;
      while (remainingSamplesInChunk == 0 && (chunkDataComplete=chunkIterator.moveNext())) {
        offset=chunkIterator.offset;
        remainingSamplesInChunk=chunkIterator.numSamples;
      }
      if (!chunkDataComplete) {
        Log.w(TAG,"Unexpected end of chunk data");
        sampleCount=i;
        offsets=Arrays.copyOf(offsets,sampleCount);
        sizes=Arrays.copyOf(sizes,sampleCount);
        timestamps=Arrays.copyOf(timestamps,sampleCount);
        flags=Arrays.copyOf(flags,sampleCount);
        break;
      }
      if (ctts != null) {
        while (remainingSamplesAtTimestampOffset == 0 && remainingTimestampOffsetChanges > 0) {
          remainingSamplesAtTimestampOffset=ctts.readUnsignedIntToInt();
          timestampOffset=ctts.readInt();
          remainingTimestampOffsetChanges--;
        }
        remainingSamplesAtTimestampOffset--;
      }
      offsets[i]=offset;
      sizes[i]=sampleSizeBox.readNextSampleSize();
      if (sizes[i] > maximumSize) {
        maximumSize=sizes[i];
      }
      timestamps[i]=timestampTimeUnits + timestampOffset;
      flags[i]=stss == null ? C.BUFFER_FLAG_KEY_FRAME : 0;
      if (i == nextSynchronizationSampleIndex) {
        flags[i]=C.BUFFER_FLAG_KEY_FRAME;
        remainingSynchronizationSamples--;
        if (remainingSynchronizationSamples > 0) {
          nextSynchronizationSampleIndex=stss.readUnsignedIntToInt() - 1;
        }
      }
      timestampTimeUnits+=timestampDeltaInTimeUnits;
      remainingSamplesAtTimestampDelta--;
      if (remainingSamplesAtTimestampDelta == 0 && remainingTimestampDeltaChanges > 0) {
        remainingSamplesAtTimestampDelta=stts.readUnsignedIntToInt();
        timestampDeltaInTimeUnits=stts.readInt();
        remainingTimestampDeltaChanges--;
      }
      offset+=sizes[i];
      remainingSamplesInChunk--;
    }
    duration=timestampTimeUnits + timestampOffset;
    boolean isCttsValid=true;
    while (remainingTimestampOffsetChanges > 0) {
      if (ctts.readUnsignedIntToInt() != 0) {
        isCttsValid=false;
        break;
      }
      ctts.readInt();
      remainingTimestampOffsetChanges--;
    }
    if (remainingSynchronizationSamples != 0 || remainingSamplesAtTimestampDelta != 0 || remainingSamplesInChunk != 0 || remainingTimestampDeltaChanges != 0 || remainingSamplesAtTimestampOffset != 0 || !isCttsValid) {
      Log.w(TAG,"Inconsistent stbl box for track " + track.id + ": remainingSynchronizationSamples " + remainingSynchronizationSamples + ", remainingSamplesAtTimestampDelta " + remainingSamplesAtTimestampDelta + ", remainingSamplesInChunk " + remainingSamplesInChunk + ", remainingTimestampDeltaChanges " + remainingTimestampDeltaChanges + ", remainingSamplesAtTimestampOffset " + remainingSamplesAtTimestampOffset + (!isCttsValid ? ", ctts invalid" : ""));
    }
  }
 else {
    long[] chunkOffsetsBytes=new long[chunkIterator.length];
    int[] chunkSampleCounts=new int[chunkIterator.length];
    while (chunkIterator.moveNext()) {
      chunkOffsetsBytes[chunkIterator.index]=chunkIterator.offset;
      chunkSampleCounts[chunkIterator.index]=chunkIterator.numSamples;
    }
    int fixedSampleSize=Util.getPcmFrameSize(track.format.pcmEncoding,track.format.channelCount);
    FixedSampleSizeRechunker.Results rechunkedResults=FixedSampleSizeRechunker.rechunk(fixedSampleSize,chunkOffsetsBytes,chunkSampleCounts,timestampDeltaInTimeUnits);
    offsets=rechunkedResults.offsets;
    sizes=rechunkedResults.sizes;
    maximumSize=rechunkedResults.maximumSize;
    timestamps=rechunkedResults.timestamps;
    flags=rechunkedResults.flags;
    duration=rechunkedResults.duration;
  }
  long durationUs=Util.scaleLargeTimestamp(duration,C.MICROS_PER_SECOND,track.timescale);
  if (track.editListDurations == null || gaplessInfoHolder.hasGaplessInfo()) {
    Util.scaleLargeTimestampsInPlace(timestamps,C.MICROS_PER_SECOND,track.timescale);
    return new TrackSampleTable(track,offsets,sizes,maximumSize,timestamps,flags,durationUs);
  }
  if (track.editListDurations.length == 1 && track.type == C.TRACK_TYPE_AUDIO && timestamps.length >= 2) {
    long editStartTime=track.editListMediaTimes[0];
    long editEndTime=editStartTime + Util.scaleLargeTimestamp(track.editListDurations[0],track.timescale,track.movieTimescale);
    if (canApplyEditWithGaplessInfo(timestamps,duration,editStartTime,editEndTime)) {
      long paddingTimeUnits=duration - editEndTime;
      long encoderDelay=Util.scaleLargeTimestamp(editStartTime - timestamps[0],track.format.sampleRate,track.timescale);
      long encoderPadding=Util.scaleLargeTimestamp(paddingTimeUnits,track.format.sampleRate,track.timescale);
      if ((encoderDelay != 0 || encoderPadding != 0) && encoderDelay <= Integer.MAX_VALUE && encoderPadding <= Integer.MAX_VALUE) {
        gaplessInfoHolder.encoderDelay=(int)encoderDelay;
        gaplessInfoHolder.encoderPadding=(int)encoderPadding;
        Util.scaleLargeTimestampsInPlace(timestamps,C.MICROS_PER_SECOND,track.timescale);
        long editedDurationUs=Util.scaleLargeTimestamp(track.editListDurations[0],C.MICROS_PER_SECOND,track.movieTimescale);
        return new TrackSampleTable(track,offsets,sizes,maximumSize,timestamps,flags,editedDurationUs);
      }
    }
  }
  if (track.editListDurations.length == 1 && track.editListDurations[0] == 0) {
    long editStartTime=track.editListMediaTimes[0];
    for (int i=0; i < timestamps.length; i++) {
      timestamps[i]=Util.scaleLargeTimestamp(timestamps[i] - editStartTime,C.MICROS_PER_SECOND,track.timescale);
    }
    durationUs=Util.scaleLargeTimestamp(duration - editStartTime,C.MICROS_PER_SECOND,track.timescale);
    return new TrackSampleTable(track,offsets,sizes,maximumSize,timestamps,flags,durationUs);
  }
  boolean omitClippedSample=track.type == C.TRACK_TYPE_AUDIO;
  int editedSampleCount=0;
  int nextSampleIndex=0;
  boolean copyMetadata=false;
  int[] startIndices=new int[track.editListDurations.length];
  int[] endIndices=new int[track.editListDurations.length];
  for (int i=0; i < track.editListDurations.length; i++) {
    long editMediaTime=track.editListMediaTimes[i];
    if (editMediaTime != -1) {
      long editDuration=Util.scaleLargeTimestamp(track.editListDurations[i],track.timescale,track.movieTimescale);
      startIndices[i]=Util.binarySearchCeil(timestamps,editMediaTime,true,true);
      endIndices[i]=Util.binarySearchCeil(timestamps,editMediaTime + editDuration,omitClippedSample,false);
      while (startIndices[i] < endIndices[i] && (flags[startIndices[i]] & C.BUFFER_FLAG_KEY_FRAME) == 0) {
        startIndices[i]++;
      }
      editedSampleCount+=endIndices[i] - startIndices[i];
      copyMetadata|=nextSampleIndex != startIndices[i];
      nextSampleIndex=endIndices[i];
    }
  }
  copyMetadata|=editedSampleCount != sampleCount;
  long[] editedOffsets=copyMetadata ? new long[editedSampleCount] : offsets;
  int[] editedSizes=copyMetadata ? new int[editedSampleCount] : sizes;
  int editedMaximumSize=copyMetadata ? 0 : maximumSize;
  int[] editedFlags=copyMetadata ? new int[editedSampleCount] : flags;
  long[] editedTimestamps=new long[editedSampleCount];
  long pts=0;
  int sampleIndex=0;
  for (int i=0; i < track.editListDurations.length; i++) {
    long editMediaTime=track.editListMediaTimes[i];
    int startIndex=startIndices[i];
    int endIndex=endIndices[i];
    if (copyMetadata) {
      int count=endIndex - startIndex;
      System.arraycopy(offsets,startIndex,editedOffsets,sampleIndex,count);
      System.arraycopy(sizes,startIndex,editedSizes,sampleIndex,count);
      System.arraycopy(flags,startIndex,editedFlags,sampleIndex,count);
    }
    for (int j=startIndex; j < endIndex; j++) {
      long ptsUs=Util.scaleLargeTimestamp(pts,C.MICROS_PER_SECOND,track.movieTimescale);
      long timeInSegmentUs=Util.scaleLargeTimestamp(timestamps[j] - editMediaTime,C.MICROS_PER_SECOND,track.timescale);
      editedTimestamps[sampleIndex]=ptsUs + timeInSegmentUs;
      if (copyMetadata && editedSizes[sampleIndex] > editedMaximumSize) {
        editedMaximumSize=sizes[j];
      }
      sampleIndex++;
    }
    pts+=track.editListDurations[i];
  }
  long editedDurationUs=Util.scaleLargeTimestamp(pts,C.MICROS_PER_SECOND,track.movieTimescale);
  return new TrackSampleTable(track,editedOffsets,editedSizes,editedMaximumSize,editedTimestamps,editedFlags,editedDurationUs);
}
