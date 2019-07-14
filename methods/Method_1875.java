/** 
 * Reads the content of the input stream until specified marker is found. Marker will be consumed and the input stream will be positioned after the specified marker.
 * @param is the input stream to read bytes from
 * @param markerToFind the marker we are looking for
 * @return boolean: whether or not we found the expected marker from input stream.
 */
public static boolean moveToMarker(InputStream is,int markerToFind) throws IOException {
  Preconditions.checkNotNull(is);
  while (StreamProcessor.readPackedInt(is,1,false) == MARKER_FIRST_BYTE) {
    int marker=MARKER_FIRST_BYTE;
    while (marker == MARKER_FIRST_BYTE) {
      marker=StreamProcessor.readPackedInt(is,1,false);
    }
    if (markerToFind == MARKER_SOFn && isSOFn(marker)) {
      return true;
    }
    if (marker == markerToFind) {
      return true;
    }
    if (marker == MARKER_SOI || marker == MARKER_TEM) {
      continue;
    }
    if (marker == MARKER_EOI || marker == MARKER_SOS) {
      return false;
    }
    int length=StreamProcessor.readPackedInt(is,2,false) - 2;
    is.skip(length);
  }
  return false;
}
