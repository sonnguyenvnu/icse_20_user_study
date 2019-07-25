/** 
 * Creates a new  {@link ImageInfo} instance populated with the informationin the parameters.
 * @param width the width of the image in pixels.
 * @param height the height of the image in pixels.
 * @param format the {@link ImageFormat} for the image.
 * @param size the size of the image in bytes or {@link #SIZE_UNKNOWN}.
 * @param bitDepth the number of bits per color model component for thisimage.
 * @param numComponents the number of color model components for this image.
 * @param colorSpace the {@link ColorSpace} of this image.
 * @param colorSpaceType the {@link ColorSpaceType} of this image. Only usedif  {@code ColorSpace} is {@code null}.
 * @param metadata the {@link Metadata} describing the image.
 * @param applyExifOrientation whether or not Exif orientation should becompensated for when setting width and height. This will also reset the Exif orientation information. <b>Changes will be applied to the  {@code metadata} argument instance</b>.
 * @param imageIOSupport whether or not {@link ImageIO} can read/parse thisimage.
 * @throws ParseException if {@code format} is {@code null} and parsing theformat from  {@code metadata} fails or parsing of{@code metadata} fails.
 */
public static ImageInfo create(int width,int height,ImageFormat format,long size,int bitDepth,int numComponents,ColorSpace colorSpace,ColorSpaceType colorSpaceType,Metadata metadata,boolean applyExifOrientation,boolean imageIOSupport) throws ParseException {
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
    return new RAWInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
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
    return new GenericImageInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
case BMP:
  return new BMPInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
case CUR:
return new CURInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
case DCX:
return new PCXInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
case GIF:
return new GIFInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
case ICO:
return new ICOInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
case JPEG:
return new JPEGInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
case PCX:
return new PCXInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
case PNG:
return new PNGInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
case PSD:
return new PSDInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
case TIFF:
return new TIFFInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
case WEBP:
return new WebPInfo(width,height,format,size,bitDepth,numComponents,colorSpace,colorSpaceType,metadata,applyExifOrientation,imageIOSupport);
default :
throw new IllegalStateException("Format " + format + " is unknown for this ImageInfo.create()");
}
}
