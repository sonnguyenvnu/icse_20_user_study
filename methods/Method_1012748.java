/** 
 * Tries to create an  {@link ImageInfo} instance from {@link Metadata}. If {@code metadata} is null or can't be parsed, an instance with invalidvalues is created or an  {@link ParseException} is thrown depending on{@code throwOnParseFailure}. The  {@link ColorModel} in this instance willbe  {@code null}. This constructor should only be used if  {@link ImageIO}can't parse the source. Instances created with this constructor will have {@code isImageIOSupport()} set to false.
 * @param width the width of the image in pixels.
 * @param height the height of the image in pixels.
 * @param metadata the {@link Metadata} describing the image.
 * @param format the {@link ImageFormat} for the image.
 * @param size the size of the image in bytes or {@link #SIZE_UNKNOWN}.
 * @param applyExifOrientation whether or not Exif orientation should becompensated for when setting width and height. This will also reset the Exif orientation information. <b>Changes will be applied to the  {@code metadata} argument instance</b>.
 * @param throwOnParseFailure if a {@link ParseException} should be throwninstead of returning an instance with invalid resolution if parsing of resolution fails.
 * @throws ParseException if {@code format} is {@code null} and parsing theformat from  {@code metadata} fails or parsing of{@code metadata} fails.
 */
public static ImageInfo create(int width,int height,Metadata metadata,ImageFormat format,long size,boolean applyExifOrientation,boolean throwOnParseFailure) throws ParseException {
  if (format == null && metadata != null) {
    format=ImageFormat.toImageFormat(metadata);
    if (format == null) {
      throw new ParseException("Unable to determine image format from metadata");
    }
  }
  if (format == null) {
    throw new IllegalArgumentException("Both format and metadata cannot be null");
  }
  if (format.isRaw()) {
    return new RAWInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
  }
switch (format) {
case ICNS:
case IFF:
case PICT:
case PNM:
case RGBE:
case SGI:
case TGA:
case WBMP:
    return new GenericImageInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
case BMP:
  return new BMPInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
case CUR:
return new CURInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
case DCX:
return new PCXInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
case GIF:
return new GIFInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
case ICO:
return new ICOInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
case JPEG:
return new JPEGInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
case PCX:
return new PCXInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
case PNG:
return new PNGInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
case PSD:
return new PSDInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
case TIFF:
return new TIFFInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
case WEBP:
return new WebPInfo(width,height,metadata,format,size,applyExifOrientation,throwOnParseFailure);
default :
throw new IllegalStateException("Format " + format + " is unknown for ImageInfo.create()");
}
}
