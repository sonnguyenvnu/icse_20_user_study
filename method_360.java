@Override public void _XXXXX_(final BufferedImage src,final OutputStream os,Map<String,Object> params) throws ImageWriteException, IOException {
  PnmWriter writer=null;
  boolean useRawbits=true;
  if (params != null) {
    final Object useRawbitsParam=params.get(PARAM_KEY_PNM_RAWBITS);
    if (useRawbitsParam != null) {
      if (useRawbitsParam.equals(PARAM_VALUE_PNM_RAWBITS_NO)) {
        useRawbits=false;
      }
    }
    final Object subtype=params.get(PARAM_KEY_FORMAT);
    if (subtype != null) {
      if (subtype.equals(ImageFormats.PBM)) {
        writer=new PbmWriter(useRawbits);
      }
 else       if (subtype.equals(ImageFormats.PGM)) {
        writer=new PgmWriter(useRawbits);
      }
 else       if (subtype.equals(ImageFormats.PPM)) {
        writer=new PpmWriter(useRawbits);
      }
 else       if (subtype.equals(ImageFormats.PAM)) {
        writer=new PamWriter();
      }
    }
  }
  if (writer == null) {
    final boolean hasAlpha=new PaletteFactory().hasTransparency(src);
    if (hasAlpha) {
      writer=new PamWriter();
    }
 else {
      writer=new PpmWriter(useRawbits);
    }
  }
  if (params != null) {
    params=new HashMap<>(params);
  }
 else {
    params=new HashMap<>();
  }
  if (params.containsKey(PARAM_KEY_FORMAT)) {
    params.remove(PARAM_KEY_FORMAT);
  }
  if (params.containsKey(PARAM_KEY_PNM_RAWBITS)) {
    params.remove(PARAM_KEY_PNM_RAWBITS);
  }
  if (!params.isEmpty()) {
    final Object firstKey=params.keySet().iterator().next();
    throw new ImageWriteException("Unknown parameter: " + firstKey);
  }
  writer.writeImage(src,os,params);
}