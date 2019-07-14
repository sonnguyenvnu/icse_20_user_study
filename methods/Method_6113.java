public static String formapMapUrl(int account,double lat,double lon,int width,int height,boolean marker,int zoom){
  int scale=Math.min(2,(int)Math.ceil(AndroidUtilities.density));
  int provider=MessagesController.getInstance(account).mapProvider;
  if (provider == 1 || provider == 3) {
    String lang=null;
    String[] availableLangs=new String[]{"ru_RU","tr_TR"};
    LocaleController.LocaleInfo localeInfo=LocaleController.getInstance().getCurrentLocaleInfo();
    for (int a=0; a < availableLangs.length; a++) {
      if (availableLangs[a].toLowerCase().contains(localeInfo.shortName)) {
        lang=availableLangs[a];
      }
    }
    if (lang == null) {
      lang="en_US";
    }
    if (marker) {
      return String.format(Locale.US,"https://static-maps.yandex.ru/1.x/?ll=%.6f,%.6f&z=%d&size=%d,%d&l=map&scale=%d&pt=%.6f,%.6f,vkbkm&lang=%s",lon,lat,zoom,width * scale,height * scale,scale,lon,lat,lang);
    }
 else {
      return String.format(Locale.US,"https://static-maps.yandex.ru/1.x/?ll=%.6f,%.6f&z=%d&size=%d,%d&l=map&scale=%d&lang=%s",lon,lat,zoom,width * scale,height * scale,scale,lang);
    }
  }
 else {
    String k=MessagesController.getInstance(account).mapKey;
    if (!TextUtils.isEmpty(k)) {
      if (marker) {
        return String.format(Locale.US,"https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d&markers=color:red%%7Csize:mid%%7C%.6f,%.6f&sensor=false&key=%s",lat,lon,zoom,width,height,scale,lat,lon,k);
      }
 else {
        return String.format(Locale.US,"https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d&key=%s",lat,lon,zoom,width,height,scale,k);
      }
    }
 else {
      if (marker) {
        return String.format(Locale.US,"https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d&markers=color:red%%7Csize:mid%%7C%.6f,%.6f&sensor=false",lat,lon,zoom,width,height,scale,lat,lon);
      }
 else {
        return String.format(Locale.US,"https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d",lat,lon,zoom,width,height,scale);
      }
    }
  }
}
