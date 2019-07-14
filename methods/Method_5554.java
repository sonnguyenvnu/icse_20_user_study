/** 
 * Decodes a subtitling packet, returning a list of parsed  {@link Cue}s.
 * @param data The subtitling packet data to decode.
 * @param limit The limit in {@code data} at which to stop decoding.
 * @return The parsed {@link Cue}s.
 */
public List<Cue> decode(byte[] data,int limit){
  ParsableBitArray dataBitArray=new ParsableBitArray(data,limit);
  while (dataBitArray.bitsLeft() >= 48 && dataBitArray.readBits(8) == 0x0F) {
    parseSubtitlingSegment(dataBitArray,subtitleService);
  }
  if (subtitleService.pageComposition == null) {
    return Collections.emptyList();
  }
  DisplayDefinition displayDefinition=subtitleService.displayDefinition != null ? subtitleService.displayDefinition : defaultDisplayDefinition;
  if (bitmap == null || displayDefinition.width + 1 != bitmap.getWidth() || displayDefinition.height + 1 != bitmap.getHeight()) {
    bitmap=Bitmap.createBitmap(displayDefinition.width + 1,displayDefinition.height + 1,Bitmap.Config.ARGB_8888);
    canvas.setBitmap(bitmap);
  }
  List<Cue> cues=new ArrayList<>();
  SparseArray<PageRegion> pageRegions=subtitleService.pageComposition.regions;
  for (int i=0; i < pageRegions.size(); i++) {
    PageRegion pageRegion=pageRegions.valueAt(i);
    int regionId=pageRegions.keyAt(i);
    RegionComposition regionComposition=subtitleService.regions.get(regionId);
    int baseHorizontalAddress=pageRegion.horizontalAddress + displayDefinition.horizontalPositionMinimum;
    int baseVerticalAddress=pageRegion.verticalAddress + displayDefinition.verticalPositionMinimum;
    int clipRight=Math.min(baseHorizontalAddress + regionComposition.width,displayDefinition.horizontalPositionMaximum);
    int clipBottom=Math.min(baseVerticalAddress + regionComposition.height,displayDefinition.verticalPositionMaximum);
    canvas.clipRect(baseHorizontalAddress,baseVerticalAddress,clipRight,clipBottom,Region.Op.REPLACE);
    ClutDefinition clutDefinition=subtitleService.cluts.get(regionComposition.clutId);
    if (clutDefinition == null) {
      clutDefinition=subtitleService.ancillaryCluts.get(regionComposition.clutId);
      if (clutDefinition == null) {
        clutDefinition=defaultClutDefinition;
      }
    }
    SparseArray<RegionObject> regionObjects=regionComposition.regionObjects;
    for (int j=0; j < regionObjects.size(); j++) {
      int objectId=regionObjects.keyAt(j);
      RegionObject regionObject=regionObjects.valueAt(j);
      ObjectData objectData=subtitleService.objects.get(objectId);
      if (objectData == null) {
        objectData=subtitleService.ancillaryObjects.get(objectId);
      }
      if (objectData != null) {
        Paint paint=objectData.nonModifyingColorFlag ? null : defaultPaint;
        paintPixelDataSubBlocks(objectData,clutDefinition,regionComposition.depth,baseHorizontalAddress + regionObject.horizontalPosition,baseVerticalAddress + regionObject.verticalPosition,paint,canvas);
      }
    }
    if (regionComposition.fillFlag) {
      int color;
      if (regionComposition.depth == REGION_DEPTH_8_BIT) {
        color=clutDefinition.clutEntries8Bit[regionComposition.pixelCode8Bit];
      }
 else       if (regionComposition.depth == REGION_DEPTH_4_BIT) {
        color=clutDefinition.clutEntries4Bit[regionComposition.pixelCode4Bit];
      }
 else {
        color=clutDefinition.clutEntries2Bit[regionComposition.pixelCode2Bit];
      }
      fillRegionPaint.setColor(color);
      canvas.drawRect(baseHorizontalAddress,baseVerticalAddress,baseHorizontalAddress + regionComposition.width,baseVerticalAddress + regionComposition.height,fillRegionPaint);
    }
    Bitmap cueBitmap=Bitmap.createBitmap(bitmap,baseHorizontalAddress,baseVerticalAddress,regionComposition.width,regionComposition.height);
    cues.add(new Cue(cueBitmap,(float)baseHorizontalAddress / displayDefinition.width,Cue.ANCHOR_TYPE_START,(float)baseVerticalAddress / displayDefinition.height,Cue.ANCHOR_TYPE_START,(float)regionComposition.width / displayDefinition.width,(float)regionComposition.height / displayDefinition.height));
    canvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
  }
  return cues;
}
