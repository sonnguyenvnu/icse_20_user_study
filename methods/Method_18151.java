@Override public void applyAttributes(TypedArray a){
  for (int i=0, size=a.getIndexCount(); i < size; i++) {
    final int attr=a.getIndex(i);
    if (attr == R.styleable.ComponentLayout_android_layout_width) {
      int width=a.getLayoutDimension(attr,-1);
      if (width >= 0) {
        widthPx(width);
      }
    }
 else     if (attr == R.styleable.ComponentLayout_android_layout_height) {
      int height=a.getLayoutDimension(attr,-1);
      if (height >= 0) {
        heightPx(height);
      }
    }
 else     if (attr == R.styleable.ComponentLayout_android_minHeight) {
      minHeightPx(a.getDimensionPixelSize(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_minWidth) {
      minWidthPx(a.getDimensionPixelSize(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_paddingLeft) {
      paddingPx(LEFT,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_paddingTop) {
      paddingPx(TOP,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_paddingRight) {
      paddingPx(RIGHT,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_paddingBottom) {
      paddingPx(BOTTOM,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_paddingStart && SUPPORTS_RTL) {
      paddingPx(START,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_paddingEnd && SUPPORTS_RTL) {
      paddingPx(END,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_padding) {
      paddingPx(ALL,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_layout_marginLeft) {
      marginPx(LEFT,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_layout_marginTop) {
      marginPx(TOP,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_layout_marginRight) {
      marginPx(RIGHT,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_layout_marginBottom) {
      marginPx(BOTTOM,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_layout_marginStart && SUPPORTS_RTL) {
      marginPx(START,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_layout_marginEnd && SUPPORTS_RTL) {
      marginPx(END,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_layout_margin) {
      marginPx(ALL,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_importantForAccessibility && SDK_INT >= JELLY_BEAN) {
      importantForAccessibility(a.getInt(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_android_duplicateParentState) {
      duplicateParentState(a.getBoolean(attr,false));
    }
 else     if (attr == R.styleable.ComponentLayout_android_background) {
      if (TypedArrayUtils.isColorAttribute(a,R.styleable.ComponentLayout_android_background)) {
        backgroundColor(a.getColor(attr,0));
      }
 else {
        backgroundRes(a.getResourceId(attr,-1));
      }
    }
 else     if (attr == R.styleable.ComponentLayout_android_foreground) {
      if (TypedArrayUtils.isColorAttribute(a,R.styleable.ComponentLayout_android_foreground)) {
        foregroundColor(a.getColor(attr,0));
      }
 else {
        foregroundRes(a.getResourceId(attr,-1));
      }
    }
 else     if (attr == R.styleable.ComponentLayout_android_contentDescription) {
      getOrCreateNodeInfo().setContentDescription(a.getString(attr));
    }
 else     if (attr == R.styleable.ComponentLayout_flex_direction) {
      flexDirection(YogaFlexDirection.fromInt(a.getInteger(attr,0)));
    }
 else     if (attr == R.styleable.ComponentLayout_flex_wrap) {
      wrap(YogaWrap.fromInt(a.getInteger(attr,0)));
    }
 else     if (attr == R.styleable.ComponentLayout_flex_justifyContent) {
      justifyContent(YogaJustify.fromInt(a.getInteger(attr,0)));
    }
 else     if (attr == R.styleable.ComponentLayout_flex_alignItems) {
      alignItems(YogaAlign.fromInt(a.getInteger(attr,0)));
    }
 else     if (attr == R.styleable.ComponentLayout_flex_alignSelf) {
      alignSelf(YogaAlign.fromInt(a.getInteger(attr,0)));
    }
 else     if (attr == R.styleable.ComponentLayout_flex_positionType) {
      positionType(YogaPositionType.fromInt(a.getInteger(attr,0)));
    }
 else     if (attr == R.styleable.ComponentLayout_flex) {
      final float flex=a.getFloat(attr,-1);
      if (flex >= 0f) {
        flex(flex);
      }
    }
 else     if (attr == R.styleable.ComponentLayout_flex_left) {
      positionPx(LEFT,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_flex_top) {
      positionPx(TOP,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_flex_right) {
      positionPx(RIGHT,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_flex_bottom) {
      positionPx(BOTTOM,a.getDimensionPixelOffset(attr,0));
    }
 else     if (attr == R.styleable.ComponentLayout_flex_layoutDirection) {
      final int layoutDirection=a.getInteger(attr,-1);
      layoutDirection(YogaDirection.fromInt(layoutDirection));
    }
  }
}
