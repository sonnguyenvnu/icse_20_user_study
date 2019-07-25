@Override public HSBK value(ByteBuffer bytes){
  int hue=FIELD_HUE.value(bytes);
  int saturation=FIELD_SATURATION.value(bytes);
  int brightness=FIELD_BRIGHTNESS.value(bytes);
  int kelvin=FIELD_KELVIN.value(bytes);
  return new HSBK(hue,saturation,brightness,kelvin);
}
