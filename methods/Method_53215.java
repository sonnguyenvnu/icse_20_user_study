HttpParameter[] asHttpParameterArray(){
  ArrayList<HttpParameter> params=new ArrayList<HttpParameter>(12);
  appendParameter("id",statusId,params);
  appendParameter("url",url,params);
  appendParameter("maxwidth",maxWidth,params);
  params.add(new HttpParameter("hide_media",hideMedia));
  params.add(new HttpParameter("hide_thread",hideThread));
  params.add(new HttpParameter("omit_script",omitScript));
  params.add(new HttpParameter("align",align.name().toLowerCase()));
  if (related.length > 0) {
    appendParameter("related",StringUtil.join(related),params);
  }
  appendParameter("lang",lang,params);
  if (widgetType != WidgetType.NONE) {
    params.add(new HttpParameter("widget_type",widgetType.name().toLowerCase()));
    params.add(new HttpParameter("hide_tweet",hideTweet));
  }
  HttpParameter[] paramArray=new HttpParameter[params.size()];
  return params.toArray(paramArray);
}
