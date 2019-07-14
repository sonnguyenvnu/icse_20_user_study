/** 
 * ( begin auto-generated from PImage_filter.xml ) Filters an image as defined by one of the following modes:<br /><br />THRESHOLD - converts the image to black and white pixels depending if they are above or below the threshold defined by the level parameter. The level must be between 0.0 (black) and 1.0(white). If no level is specified, 0.5 is used.<br /> <br /> GRAY - converts any colors in the image to grayscale equivalents<br /> <br /> INVERT - sets each pixel to its inverse value<br /> <br /> POSTERIZE - limits each channel of the image to the number of colors specified as the level parameter<br /> <br /> BLUR - executes a Guassian blur with the level parameter specifying the extent of the blurring. If no level parameter is used, the blur is equivalent to Guassian blur of radius 1<br /> <br /> OPAQUE - sets the alpha channel to entirely opaque<br /> <br /> ERODE - reduces the light areas with the amount defined by the level parameter<br /> <br /> DILATE - increases the light areas with the amount defined by the level parameter ( end auto-generated ) <h3>Advanced</h3> Method to apply a variety of basic filters to this image. <P> <UL> <LI>filter(BLUR) provides a basic blur. <LI>filter(GRAY) converts the image to grayscale based on luminance. <LI>filter(INVERT) will invert the color components in the image. <LI>filter(OPAQUE) set all the high bits in the image to opaque <LI>filter(THRESHOLD) converts the image to black and white. <LI>filter(DILATE) grow white/light areas <LI>filter(ERODE) shrink white/light areas </UL> Luminance conversion code contributed by <A HREF="http://www.toxi.co.uk">toxi</A> <P/> Gaussian blur code contributed by <A HREF="http://incubator.quasimondo.com">Mario Klingemann</A>
 * @webref image:pixels
 * @brief Converts the image to grayscale or black and white
 * @usage web_application
 * @param kind Either THRESHOLD, GRAY, OPAQUE, INVERT, POSTERIZE, BLUR, ERODE, or DILATE
 * @param param unique for each, see above
 */
public void filter(int kind,float param){
  loadPixels();
switch (kind) {
case BLUR:
    if (format == ALPHA)     blurAlpha(param);
 else     if (format == ARGB)     blurARGB(param);
 else     blurRGB(param);
  break;
case GRAY:
throw new RuntimeException("Use filter(GRAY) instead of " + "filter(GRAY, param)");
case INVERT:
throw new RuntimeException("Use filter(INVERT) instead of " + "filter(INVERT, param)");
case OPAQUE:
throw new RuntimeException("Use filter(OPAQUE) instead of " + "filter(OPAQUE, param)");
case POSTERIZE:
int levels=(int)param;
if ((levels < 2) || (levels > 255)) {
throw new RuntimeException("Levels must be between 2 and 255 for " + "filter(POSTERIZE, levels)");
}
int levels1=levels - 1;
for (int i=0; i < pixels.length; i++) {
int rlevel=(pixels[i] >> 16) & 0xff;
int glevel=(pixels[i] >> 8) & 0xff;
int blevel=pixels[i] & 0xff;
rlevel=(((rlevel * levels) >> 8) * 255) / levels1;
glevel=(((glevel * levels) >> 8) * 255) / levels1;
blevel=(((blevel * levels) >> 8) * 255) / levels1;
pixels[i]=((0xff000000 & pixels[i]) | (rlevel << 16) | (glevel << 8) | blevel);
}
break;
case THRESHOLD:
int thresh=(int)(param * 255);
for (int i=0; i < pixels.length; i++) {
int max=Math.max((pixels[i] & RED_MASK) >> 16,Math.max((pixels[i] & GREEN_MASK) >> 8,(pixels[i] & BLUE_MASK)));
pixels[i]=(pixels[i] & ALPHA_MASK) | ((max < thresh) ? 0x000000 : 0xffffff);
}
break;
case ERODE:
throw new RuntimeException("Use filter(ERODE) instead of " + "filter(ERODE, param)");
case DILATE:
throw new RuntimeException("Use filter(DILATE) instead of " + "filter(DILATE, param)");
}
updatePixels();
}
