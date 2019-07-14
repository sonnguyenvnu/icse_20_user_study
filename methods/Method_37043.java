@NonNull @Override public Card parseSingleGroup(@Nullable JSONObject data,@NonNull final ServiceManager serviceManager){
  if (TangramBuilder.isPrintLog() && serviceManager == null) {
    throw new RuntimeException("serviceManager is null when parsing card!");
  }
  if (data == null) {
    return Card.NaN;
  }
  checkCardResolverAndMVHelper(serviceManager);
  String cardType=parseCardType(data);
  if (!TextUtils.isEmpty(cardType)) {
    Card card=cardResolver.create(cardType);
    if (card == null) {
      card=new WrapCellCard();
      cardType=TangramBuilder.TYPE_CONTAINER_1C_FLOW;
      JSONObject wrapCardJson=new JSONObject();
      wrapCardJson.put("type",TangramBuilder.TYPE_CONTAINER_1C_FLOW);
      JSONArray itemArray=new JSONArray();
      itemArray.add(data);
      wrapCardJson.put("items",itemArray);
      data=wrapCardJson;
    }
    card.dataParser=this;
    card.serviceManager=serviceManager;
    card.extras=data;
    card.stringType=cardType;
    card.loadParams=data.getJSONObject(KEY_API_LOAD_PARAMS);
    Map<String,ComponentInfo> infoMap=parseComponentInfo(data);
    parseCard(card,data,serviceManager,infoMap);
    JSONObject styleJson=data.getJSONObject(KEY_STYLE);
    if (card instanceof GridCard) {
      GridCard gridCard=(GridCard)card;
      GridCard.GridStyle style=new GridCard.GridStyle();
      if (styleJson != null) {
        parseStyle(style,styleJson);
        style.column=styleJson.getIntValue(KEY_COLUMN);
        style.autoExpand=styleJson.getBooleanValue(KEY_AUTO_EXPAND);
        JSONArray jsonCols=styleJson.getJSONArray(KEY_COLS);
        if (jsonCols != null) {
          style.cols=new float[jsonCols.size()];
          for (int i=0; i < style.cols.length; i++) {
            style.cols[i]=(float)jsonCols.getDoubleValue(i);
          }
        }
 else {
          style.cols=new float[0];
        }
        style.hGap=Style.parseSize(styleJson.getString(KEY_H_GAP),0);
        style.vGap=Style.parseSize(styleJson.getString(KEY_V_GAP),0);
        if (style.column > 0) {
          gridCard.mColumn=style.column;
        }
        for (        BaseCell cell : card.mCells) {
          if (cell.style.extras != null) {
            int colSpan=cell.style.extras.getIntValue("colspan");
            if (colSpan == 0) {
              colSpan=1;
            }
            cell.colSpan=colSpan;
          }
        }
      }
      card=gridCard;
      card.style=style;
    }
 else     if (card instanceof BannerCard) {
      BannerCard bannerCard=(BannerCard)card;
      if (bannerCard.cell == null) {
        bannerCard.cell=new BannerCell();
        bannerCard.cell.serviceManager=serviceManager;
      }
      try {
        bannerCard.cell.stringType=TYPE_CAROUSEL_CELL_COMPACT;
        if (!bannerCard.getCells().isEmpty()) {
          bannerCard.cell.mCells.addAll(bannerCard.getCells());
          bannerCard.setCells(bannerCard.getCells());
        }
      }
 catch (      Exception e) {
        if (errorSupport == null) {
          errorSupport=serviceManager.getService(InternalErrorSupport.class);
        }
        HashMap<String,Object> params=new HashMap<>();
        params.put("exception",Log.getStackTraceString(e));
        errorSupport.onError(InternalErrorSupport.ERROR_PARSE_CARDS_ERROR,"Parse banner card error.",params);
        e.printStackTrace();
        bannerCard.setCells(null);
      }
      Style style=new Style();
      if (styleJson != null) {
        bannerCard.cell.setIndicatorRadius(Style.parseSize(styleJson.getString(ATTR_INDICATOR_RADIUS),0));
        bannerCard.cell.setIndicatorColor(Style.parseColor(styleJson.getString(ATTR_INDICATOR_COLOR),Color.TRANSPARENT));
        bannerCard.cell.setIndicatorDefaultColor(Style.parseColor(styleJson.getString(ATTR_INDICATOR_DEFAULT_INDICATOR_COLOR),Color.TRANSPARENT));
        bannerCard.cell.setAutoScrollInternal(styleJson.getIntValue(ATTR_AUTOSCROLL));
        bannerCard.cell.setSpecialInterval(styleJson.getJSONObject(ATTR_SPECIAL_INTERVAL));
        bannerCard.cell.setInfinite(styleJson.getBooleanValue(ATTR_INFINITE));
        bannerCard.cell.setInfiniteMinCount(styleJson.getIntValue(ATTR_INFINITE_MIN_COUNT));
        bannerCard.cell.setIndicatorFocus(styleJson.getString(ATTR_INDICATOR_FOCUS));
        bannerCard.cell.setIndicatorNor(styleJson.getString(ATTR_INDICATOR_NORMAL));
        bannerCard.cell.setIndicatorGravity(styleJson.getString(ATTR_INDICATOR_GRA));
        bannerCard.cell.setIndicatorPos(styleJson.getString(ATTR_INDICATOR_POS));
        bannerCard.cell.setIndicatorGap(Style.parseSize(styleJson.getString(ATTR_INDICATOR_GAP),Style.dp2px(6)));
        bannerCard.cell.setIndicatorMargin(Style.parseSize(styleJson.getString(ATTR_INDICATOR_MARGIN),0));
        bannerCard.cell.setIndicatorHeight(Style.parseSize(styleJson.getString(ATTR_INDICATOR_HEIGHT),0));
        bannerCard.cell.setPageWidth(Utils.getJsonFloatValue(styleJson,ATTR_PAGE_WIDTH));
        bannerCard.cell.sethGap(Style.parseSize(styleJson.getString(ATTR_HGAP),0));
        bannerCard.cell.itemRatio=Utils.getJsonDoubleValue(styleJson,ATTR_ITEM_RATIO);
        bannerCard.cell.itemMargin[0]=Style.parseSize(styleJson.getString(ATTR_ITEM_MARGIN_LEFT),0);
        bannerCard.cell.itemMargin[1]=Style.parseSize(styleJson.getString(ATTR_ITEM_MARGIN_RIGHT),0);
        parseStyle(style,styleJson);
      }
      card.style=style;
      bannerCard.cell.setRatio(style.aspectRatio);
      bannerCard.cell.margin=style.margin;
      bannerCard.cell.height=style.height;
    }
 else     if (card instanceof OnePlusNCard) {
      ColumnStyle style=new ColumnStyle();
      if (styleJson != null) {
        parseStyle(style,styleJson);
        JSONArray jsonCols=styleJson.getJSONArray(KEY_COLS);
        if (jsonCols != null) {
          style.cols=new float[jsonCols.size()];
          for (int i=0; i < style.cols.length; i++) {
            style.cols[i]=(float)jsonCols.getDoubleValue(i);
          }
        }
 else {
          style.cols=new float[0];
        }
        JSONArray jsonRows=styleJson.getJSONArray(KEY_ROWS);
        if (jsonRows != null) {
          style.rows=new float[jsonRows.size()];
          for (int i=0; i < style.rows.length; i++) {
            style.rows[i]=(float)jsonRows.getDoubleValue(i);
          }
        }
 else {
          style.rows=new float[0];
        }
      }
      card.style=style;
    }
 else     if (card instanceof FixLinearScrollCard) {
      FixLinearScrollCard fixLinearScrollCard=(FixLinearScrollCard)card;
      FixCard.FixStyle fixStyle=new FixCard.FixStyle();
      if (styleJson != null) {
        parseStyle(fixStyle,styleJson);
        String showTypeStr=styleJson.getString(KEY_SHOW_TYPE);
        if (TextUtils.isEmpty(showTypeStr)) {
          showTypeStr="top_left";
        }
 else {
          showTypeStr=showTypeStr.toLowerCase();
        }
        String align=styleJson.getString(KEY_ALIGN);
        if (TextUtils.isEmpty(align)) {
          align="always";
        }
 else {
          align=align.toLowerCase();
        }
        Boolean sketchMeasure=styleJson.getBoolean(KEY_SKETCH_MEASURE);
        if (sketchMeasure == null) {
          fixStyle.sketchMeasure=true;
        }
 else {
          fixStyle.sketchMeasure=sketchMeasure;
        }
        if ("showonenter".equals(showTypeStr)) {
          fixStyle.showType=SHOW_ON_ENTER;
        }
 else         if ("showonleave".equals(showTypeStr)) {
          fixStyle.showType=SHOW_ON_LEAVE;
        }
 else         if ("always".equals(showTypeStr)) {
          fixStyle.showType=SHOW_ALWAYS;
        }
        if ("top_left".equals(align)) {
          fixStyle.alignType=TOP_LEFT;
        }
 else         if ("top_right".equals(align)) {
          fixStyle.alignType=TOP_RIGHT;
        }
 else         if ("bottom_left".equals(align)) {
          fixStyle.alignType=BOTTOM_LEFT;
        }
 else         if ("bottom_right".equals(align)) {
          fixStyle.alignType=BOTTOM_RIGHT;
        }
        fixStyle.x=Style.parseSize(styleJson.getString(KEY_X),0);
        fixStyle.y=Style.parseSize(styleJson.getString(KEY_Y),0);
      }
      fixLinearScrollCard.mFixStyle=fixStyle;
    }
 else     if (card instanceof StickyEndCard) {
      StickyCard.StickyStyle stickyStyle=new StickyCard.StickyStyle(false);
      if (styleJson != null) {
        stickyStyle.offset=Style.parseSize(styleJson.getString("offset"),0);
      }
      card.style=stickyStyle;
    }
 else     if (card instanceof StickyCard) {
      StickyCard.StickyStyle stickyStyle=new StickyCard.StickyStyle(true);
      if (styleJson != null) {
        String sticky=styleJson.getString(KEY_STICKY);
        if (TextUtils.isEmpty(sticky)) {
          sticky=stickyStyle.stickyStart ? STICKY_START : STICKY_END;
        }
        stickyStyle.stickyStart=STICKY_START.equalsIgnoreCase(sticky);
        stickyStyle.offset=Style.parseSize(styleJson.getString("offset"),0);
      }
      card.style=stickyStyle;
    }
 else     if (card instanceof FixCard) {
      FixCard.FixStyle fixStyle=new FixCard.FixStyle();
      if (styleJson != null) {
        parseStyle(fixStyle,styleJson);
        String showTypeStr=styleJson.getString(KEY_SHOW_TYPE);
        if (TextUtils.isEmpty(showTypeStr)) {
          showTypeStr="top_left";
        }
 else {
          showTypeStr=showTypeStr.toLowerCase();
        }
        String align=styleJson.getString(KEY_ALIGN);
        if (TextUtils.isEmpty(align)) {
          align="always";
        }
 else {
          align=align.toLowerCase();
        }
        Boolean sketchMeasure=styleJson.getBoolean(KEY_SKETCH_MEASURE);
        if (sketchMeasure == null) {
          fixStyle.sketchMeasure=true;
        }
 else {
          fixStyle.sketchMeasure=sketchMeasure;
        }
        if ("showonenter".equals(showTypeStr)) {
          fixStyle.showType=SHOW_ON_ENTER;
        }
 else         if ("showonleave".equals(showTypeStr)) {
          fixStyle.showType=SHOW_ON_LEAVE;
        }
 else         if ("always".equals(showTypeStr)) {
          fixStyle.showType=SHOW_ALWAYS;
        }
        if ("top_left".equals(align)) {
          fixStyle.alignType=TOP_LEFT;
        }
 else         if ("top_right".equals(align)) {
          fixStyle.alignType=TOP_RIGHT;
        }
 else         if ("bottom_left".equals(align)) {
          fixStyle.alignType=BOTTOM_LEFT;
        }
 else         if ("bottom_right".equals(align)) {
          fixStyle.alignType=BOTTOM_RIGHT;
        }
        fixStyle.x=Style.parseSize(styleJson.getString(KEY_X),0);
        fixStyle.y=Style.parseSize(styleJson.getString(KEY_Y),0);
      }
      card.style=fixStyle;
    }
 else     if (card instanceof LinearScrollCard) {
      LinearScrollCard linearScrollCard=(LinearScrollCard)card;
      try {
        linearScrollCard.cell.stringType=TangramBuilder.TYPE_LINEAR_SCROLL_CELL_COMPACT;
        linearScrollCard.cell.serviceManager=serviceManager;
        if (!linearScrollCard.getCells().isEmpty()) {
          linearScrollCard.cell.cells.addAll(linearScrollCard.getCells());
          linearScrollCard.setCells(linearScrollCard.getCells());
        }
      }
 catch (      Exception e) {
        if (errorSupport == null) {
          errorSupport=serviceManager.getService(InternalErrorSupport.class);
        }
        HashMap<String,Object> params=new HashMap<>();
        params.put("exception",Log.getStackTraceString(e));
        errorSupport.onError(InternalErrorSupport.ERROR_PARSE_CARDS_ERROR,"Parse linear scroll card error.",params);
        e.printStackTrace();
        linearScrollCard.setCells(null);
      }
      Style style=new Style();
      if (styleJson != null) {
        linearScrollCard.cell.pageWidth=Style.parseSize(styleJson.getString(LinearScrollCell.KEY_PAGE_WIDTH),0);
        linearScrollCard.cell.pageHeight=Style.parseSize(styleJson.getString(LinearScrollCell.KEY_PAGE_HEIGHT),0);
        linearScrollCard.cell.defaultIndicatorColor=Style.parseColor(styleJson.getString(LinearScrollCell.KEY_DEFAULT_INDICATOR_COLOR),LinearScrollCell.DEFAULT_DEFAULT_INDICATOR_COLOR);
        linearScrollCard.cell.indicatorColor=Style.parseColor(styleJson.getString(LinearScrollCell.KEY_INDICATOR_COLOR),LinearScrollCell.DEFAULT_INDICATOR_COLOR);
        if (styleJson.containsKey(LinearScrollCell.KEY_HAS_INDICATOR)) {
          linearScrollCard.cell.hasIndicator=styleJson.getBooleanValue(LinearScrollCell.KEY_HAS_INDICATOR);
        }
        linearScrollCard.cell.indicatorHeight=Style.parseSize(styleJson.getString(LinearScrollCell.KEY_INDICATOR_HEIGHT),LinearScrollCell.DEFAULT_INDICATOR_HEIGHT);
        linearScrollCard.cell.indicatorWidth=Style.parseSize(styleJson.getString(LinearScrollCell.KEY_INDICATOR_WIDTH),LinearScrollCell.DEFAULT_INDICATOR_WIDTH);
        linearScrollCard.cell.defaultIndicatorWidth=Style.parseSize(styleJson.getString(LinearScrollCell.KEY_DEFAULT_INDICATOR_WIDTH),LinearScrollCell.DEFAULT_DEFAULT_INDICATOR_WIDTH);
        linearScrollCard.cell.indicatorMargin=Style.parseSize(styleJson.getString(LinearScrollCell.KEY_INDICATOR_MARGIN),LinearScrollCell.DEFAULT_INDICATOR_MARGIN);
        linearScrollCard.cell.indicatorRadius=Style.parseSize(styleJson.getString(LinearScrollCell.KEY_INDICATOR_RADIUS),LinearScrollCell.DEFAULT_INDICATOR_RADIUS);
        if (styleJson.containsKey(LinearScrollCell.KEY_FOOTER_TYPE)) {
          linearScrollCard.cell.footerType=styleJson.getString(LinearScrollCell.KEY_FOOTER_TYPE);
        }
        linearScrollCard.cell.bgColor=Style.parseColor(styleJson.getString(KEY_BG_COLOR),Color.TRANSPARENT);
        Boolean retainScrollState=styleJson.getBoolean(LinearScrollCell.KEY_RETAIN_SCROLL_STATE);
        if (retainScrollState == null) {
          linearScrollCard.cell.retainScrollState=true;
        }
 else {
          linearScrollCard.cell.retainScrollState=retainScrollState;
        }
        linearScrollCard.cell.scrollMarginLeft=Style.parseSize(styleJson.getString(LinearScrollCell.KEY_SCROLL_MARGIN_LEFT),0);
        linearScrollCard.cell.scrollMarginRight=Style.parseSize(styleJson.getString(LinearScrollCell.KEY_SCROLL_MARGIN_RIGHT),0);
        linearScrollCard.cell.hGap=Style.parseSize(styleJson.getString(LinearScrollCell.KEY_HGAP),0);
        linearScrollCard.cell.vGap=Style.parseSize(styleJson.getString(LinearScrollCell.KEY_VGAP),0);
        linearScrollCard.cell.nativeBackgroundImage=styleJson.getString(LinearScrollCell.KEY_NATIVE_BG_IMAGE);
        Integer maxRows=styleJson.getInteger(LinearScrollCell.KEY_MAX_ROWS);
        if (maxRows == null) {
          maxRows=LinearScrollCell.DEFAULT_MAX_ROWS;
        }
        linearScrollCard.cell.maxRows=maxRows;
        try {
          linearScrollCard.cell.maxCols=(int)styleJson.getDoubleValue(LinearScrollCell.KEY_MAX_COLS);
        }
 catch (        Exception e) {
          if (errorSupport == null) {
            errorSupport=serviceManager.getService(InternalErrorSupport.class);
          }
          HashMap<String,Object> params=new HashMap<>();
          params.put("exception",Log.getStackTraceString(e));
          errorSupport.onError(InternalErrorSupport.ERROR_PARSE_CARDS_ERROR,"Parse linear scroll card max cols error.",params);
          e.printStackTrace();
        }
        parseStyle(style,styleJson);
      }
      linearScrollCard.style=style;
      card=linearScrollCard;
    }
 else     if (card instanceof StaggeredCard) {
      StaggeredCard staggeredCard=(StaggeredCard)card;
      StaggeredCard.StaggeredStyle style=new StaggeredCard.StaggeredStyle();
      if (styleJson != null) {
        parseStyle(style,styleJson);
        Integer column=styleJson.getInteger(KEY_COLUMN);
        if (column == null) {
          column=2;
        }
        style.column=column;
        style.hGap=Style.parseSize(styleJson.getString(KEY_H_GAP),0);
        style.vGap=Style.parseSize(styleJson.getString(KEY_V_GAP),0);
      }
      staggeredCard.style=style;
    }
 else     if (isCustomCard(card.stringType)) {
      parseCustomCard(card,data,serviceManager,infoMap);
    }
 else {
      Style style=new Style();
      parseStyle(style,styleJson);
      card.style=style;
    }
    if (card.isValid()) {
      if (card.style != null && card.style.slidable) {
        return new SlideCard(card);
      }
 else {
        return card;
      }
    }
  }
 else {
    LogUtils.w(TAG,"Invalid card type when parse JSON data");
  }
  return Card.NaN;
}
