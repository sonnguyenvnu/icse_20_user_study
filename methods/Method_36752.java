public void parseWith(@Nullable JSONObject data){
  if (data != null) {
    extras=data;
    forLabel=data.optString(KEY_FOR_LABEL,"");
    setBgColor(data.optString(KEY_BG_COLOR,DEFAULT_BG_COLOR));
    String backgroundColor=data.optString(KEY_BACKGROUND_COLOR);
    if (!TextUtils.isEmpty(backgroundColor)) {
      setBgColor(backgroundColor);
    }
    if (data.has(KEY_WIDTH)) {
      String widthValue=data.optString(KEY_WIDTH);
      this.width=parseSize(widthValue,VirtualLayoutManager.LayoutParams.MATCH_PARENT);
    }
    if (data.has(KEY_HEIGHT)) {
      String heightValue=data.optString(KEY_HEIGHT);
      this.height=parseSize(heightValue,VirtualLayoutManager.LayoutParams.WRAP_CONTENT);
    }
    bgImage=data.optString(KEY_BG_IMAGE,"");
    bgImgUrl=data.optString(KEY_STYLE_BG_IMAGE,"");
    String backgroundImage=data.optString(KEY_BACKGROUND_IMAGE,"");
    if (!TextUtils.isEmpty(backgroundImage)) {
      bgImage=backgroundImage;
      bgImgUrl=backgroundImage;
    }
    aspectRatio=(float)data.optDouble(KEY_ASPECT_RATIO);
    double ratio=data.optDouble(KEY_RATIO);
    if (!Double.isNaN(ratio)) {
      aspectRatio=(float)ratio;
    }
    zIndex=data.optInt(KEY_ZINDEX,0);
    slidable=data.optBoolean(KEY_SLIDABLE);
    JSONArray marginArray=data.optJSONArray(KEY_MARGIN);
    if (marginArray != null) {
      int size=Math.min(margin.length,marginArray.length());
      for (int i=0; i < size; i++) {
        margin[i]=parseSize(marginArray.optString(i),0);
      }
      if (size > 0) {
        Arrays.fill(margin,size,margin.length,margin[size - 1]);
      }
    }
 else {
      String marginString=data.optString(KEY_MARGIN);
      if (!TextUtils.isEmpty(marginString)) {
        setMargin(marginString);
      }
    }
    JSONArray paddingArray=data.optJSONArray(KEY_PADDING);
    if (paddingArray != null) {
      int size=Math.min(padding.length,paddingArray.length());
      for (int i=0; i < size; i++) {
        padding[i]=parseSize(paddingArray.optString(i),0);
      }
      if (size > 0) {
        Arrays.fill(padding,size,padding.length,padding[size - 1]);
      }
    }
 else {
      String paddingString=data.optString(KEY_PADDING);
      if (!TextUtils.isEmpty(paddingString)) {
        setPadding(paddingString);
      }
    }
  }
}
