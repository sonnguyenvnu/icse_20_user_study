/** 
 * Calculate how much further the image can be panned in each direction. The results are set on the supplied  {@link RectF} and expressed as screen pixels. For example, if the image cannot bepanned any further towards the left, the value of  {@link RectF#left} will be set to 0.
 * @param vTarget target object for results. Re-use for efficiency.
 */
public final void getPanRemaining(RectF vTarget){
  if (!isReady()) {
    return;
  }
  float scaleWidth=scale * sWidth();
  float scaleHeight=scale * sHeight();
  if (panLimit == PAN_LIMIT_CENTER) {
    vTarget.top=Math.max(0,-(vTranslate.y - (getHeight() / 2)));
    vTarget.left=Math.max(0,-(vTranslate.x - (getWidth() / 2)));
    vTarget.bottom=Math.max(0,vTranslate.y - ((getHeight() / 2) - scaleHeight));
    vTarget.right=Math.max(0,vTranslate.x - ((getWidth() / 2) - scaleWidth));
  }
 else   if (panLimit == PAN_LIMIT_OUTSIDE) {
    vTarget.top=Math.max(0,-(vTranslate.y - getHeight()));
    vTarget.left=Math.max(0,-(vTranslate.x - getWidth()));
    vTarget.bottom=Math.max(0,vTranslate.y + scaleHeight);
    vTarget.right=Math.max(0,vTranslate.x + scaleWidth);
  }
 else {
    vTarget.top=Math.max(0,-vTranslate.y);
    vTarget.left=Math.max(0,-vTranslate.x);
    vTarget.bottom=Math.max(0,(scaleHeight + vTranslate.y) - getHeight());
    vTarget.right=Math.max(0,(scaleWidth + vTranslate.x) - getWidth());
  }
}
