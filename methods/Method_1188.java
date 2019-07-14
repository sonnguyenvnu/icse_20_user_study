/** 
 * Updates the existing hierarchy builder based on the XML attributes. This method is useful if a custom view uses different default values. In that case a builder with adjusted default values can be passed to this method and only the properties explicitly specified in XML will be overridden. The builder can be modified afterwards in case some XML attributes needs to be overridden.
 * @param builder a hierarchy builder to be updated
 * @return the modified instance of the same builder
 */
public static GenericDraweeHierarchyBuilder updateBuilder(GenericDraweeHierarchyBuilder builder,Context context,@Nullable AttributeSet attrs){
  int progressBarAutoRotateInterval=0;
  int roundedCornerRadius=0;
  boolean roundTopLeft=true;
  boolean roundTopRight=true;
  boolean roundBottomLeft=true;
  boolean roundBottomRight=true;
  boolean roundTopStart=true;
  boolean roundTopEnd=true;
  boolean roundBottomStart=true;
  boolean roundBottomEnd=true;
  if (attrs != null) {
    TypedArray gdhAttrs=context.obtainStyledAttributes(attrs,R.styleable.GenericDraweeHierarchy);
    try {
      final int indexCount=gdhAttrs.getIndexCount();
      for (int i=0; i < indexCount; i++) {
        final int attr=gdhAttrs.getIndex(i);
        if (attr == R.styleable.GenericDraweeHierarchy_actualImageScaleType) {
          builder.setActualImageScaleType(getScaleTypeFromXml(gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_placeholderImage) {
          builder.setPlaceholderImage(getDrawable(context,gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_pressedStateOverlayImage) {
          builder.setPressedStateOverlay(getDrawable(context,gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_progressBarImage) {
          builder.setProgressBarImage(getDrawable(context,gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_fadeDuration) {
          builder.setFadeDuration(gdhAttrs.getInt(attr,0));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_viewAspectRatio) {
          builder.setDesiredAspectRatio(gdhAttrs.getFloat(attr,0));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_placeholderImageScaleType) {
          builder.setPlaceholderImageScaleType(getScaleTypeFromXml(gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_retryImage) {
          builder.setRetryImage(getDrawable(context,gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_retryImageScaleType) {
          builder.setRetryImageScaleType(getScaleTypeFromXml(gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_failureImage) {
          builder.setFailureImage(getDrawable(context,gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_failureImageScaleType) {
          builder.setFailureImageScaleType(getScaleTypeFromXml(gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_progressBarImageScaleType) {
          builder.setProgressBarImageScaleType(getScaleTypeFromXml(gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_progressBarAutoRotateInterval) {
          progressBarAutoRotateInterval=gdhAttrs.getInteger(attr,progressBarAutoRotateInterval);
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_backgroundImage) {
          builder.setBackground(getDrawable(context,gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_overlayImage) {
          builder.setOverlay(getDrawable(context,gdhAttrs,attr));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundAsCircle) {
          getRoundingParams(builder).setRoundAsCircle(gdhAttrs.getBoolean(attr,false));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundedCornerRadius) {
          roundedCornerRadius=gdhAttrs.getDimensionPixelSize(attr,roundedCornerRadius);
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundTopLeft) {
          roundTopLeft=gdhAttrs.getBoolean(attr,roundTopLeft);
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundTopRight) {
          roundTopRight=gdhAttrs.getBoolean(attr,roundTopRight);
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundBottomLeft) {
          roundBottomLeft=gdhAttrs.getBoolean(attr,roundBottomLeft);
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundBottomRight) {
          roundBottomRight=gdhAttrs.getBoolean(attr,roundBottomRight);
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundTopStart) {
          roundTopStart=gdhAttrs.getBoolean(attr,roundTopStart);
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundTopEnd) {
          roundTopEnd=gdhAttrs.getBoolean(attr,roundTopEnd);
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundBottomStart) {
          roundBottomStart=gdhAttrs.getBoolean(attr,roundBottomStart);
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundBottomEnd) {
          roundBottomEnd=gdhAttrs.getBoolean(attr,roundBottomEnd);
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundWithOverlayColor) {
          getRoundingParams(builder).setOverlayColor(gdhAttrs.getColor(attr,0));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundingBorderWidth) {
          getRoundingParams(builder).setBorderWidth(gdhAttrs.getDimensionPixelSize(attr,0));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundingBorderColor) {
          getRoundingParams(builder).setBorderColor(gdhAttrs.getColor(attr,0));
        }
 else         if (attr == R.styleable.GenericDraweeHierarchy_roundingBorderPadding) {
          getRoundingParams(builder).setPadding(gdhAttrs.getDimensionPixelSize(attr,0));
        }
      }
    }
  finally {
      gdhAttrs.recycle();
      if (android.os.Build.VERSION.SDK_INT >= 17 && context.getResources().getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
        roundTopLeft=roundTopLeft && roundTopEnd;
        roundTopRight=roundTopRight && roundTopStart;
        roundBottomRight=roundBottomRight && roundBottomStart;
        roundBottomLeft=roundBottomLeft && roundBottomEnd;
      }
 else {
        roundTopLeft=roundTopLeft && roundTopStart;
        roundTopRight=roundTopRight && roundTopEnd;
        roundBottomRight=roundBottomRight && roundBottomEnd;
        roundBottomLeft=roundBottomLeft && roundBottomStart;
      }
    }
  }
  if (builder.getProgressBarImage() != null && progressBarAutoRotateInterval > 0) {
    builder.setProgressBarImage(new AutoRotateDrawable(builder.getProgressBarImage(),progressBarAutoRotateInterval));
  }
  if (roundedCornerRadius > 0) {
    getRoundingParams(builder).setCornersRadii(roundTopLeft ? roundedCornerRadius : 0,roundTopRight ? roundedCornerRadius : 0,roundBottomRight ? roundedCornerRadius : 0,roundBottomLeft ? roundedCornerRadius : 0);
  }
  return builder;
}
