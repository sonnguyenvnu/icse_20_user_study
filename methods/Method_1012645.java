public void init(File f) throws ConfigurationException {
  rootFolder=null;
  if (!loaded) {
    configuration.clear();
    loaded=load(f);
  }
  if (isUpnpAllowed() && uuid == null) {
    String id=getDeviceId();
    if (StringUtils.isNotBlank(id) && !id.contains(",")) {
      uuid=id;
    }
  }
  mimes=new HashMap<>();
  String mimeTypes=getString(MIME_TYPES_CHANGES,"");
  if (StringUtils.isNotBlank(mimeTypes)) {
    StringTokenizer st=new StringTokenizer(mimeTypes,"|");
    while (st.hasMoreTokens()) {
      String mime_change=st.nextToken().trim();
      int equals=mime_change.indexOf('=');
      if (equals > -1) {
        String old=mime_change.substring(0,equals).trim().toLowerCase();
        String nw=mime_change.substring(equals + 1).trim().toLowerCase();
        mimes.put(old,nw);
      }
    }
  }
  String s=getString(TEXTWRAP,"").toLowerCase();
  lineWidth=getIntAt(s,"width:",0);
  if (lineWidth > 0) {
    lineHeight=getIntAt(s,"height:",0);
    indent=getIntAt(s,"indent:",0);
    int whitespace=getIntAt(s,"whitespace:",9);
    int dotCount=getIntAt(s,"dots:",0);
    inset=StringUtil.fillString(whitespace,indent);
    dots=StringUtil.fillString(".",dotCount);
  }
  charMap=new HashMap<>();
  String ch=getString(CHARMAP,null);
  if (StringUtils.isNotBlank(ch)) {
    StringTokenizer st=new StringTokenizer(ch," ");
    String org="";
    while (st.hasMoreTokens()) {
      String tok=st.nextToken().trim();
      if (StringUtils.isBlank(tok)) {
        continue;
      }
      tok=tok.replaceAll("###0"," ").replaceAll("###n","\n").replaceAll("###r","\r");
      if (StringUtils.isBlank(org)) {
        org=tok;
      }
 else {
        charMap.put(org,tok);
        org="";
      }
    }
  }
  DLNAPN=new HashMap<>();
  String DLNAPNchanges=getString(DLNA_PN_CHANGES,"");
  if (StringUtils.isNotBlank(DLNAPNchanges)) {
    LOGGER.trace("Config DLNAPNchanges: " + DLNAPNchanges);
    StringTokenizer st=new StringTokenizer(DLNAPNchanges,"|");
    while (st.hasMoreTokens()) {
      String DLNAPN_change=st.nextToken().trim();
      int equals=DLNAPN_change.indexOf('=');
      if (equals > -1) {
        String old=DLNAPN_change.substring(0,equals).trim().toUpperCase();
        String nw=DLNAPN_change.substring(equals + 1).trim().toUpperCase();
        DLNAPN.put(old,nw);
      }
    }
  }
  if (f == null) {
    configuration.addProperty(MEDIAPARSERV2,true);
    configuration.addProperty(MEDIAPARSERV2_THUMB,true);
    configuration.addProperty(SUPPORTED,"f:.+");
  }
  if (isUseMediaInfo()) {
    formatConfiguration=new FormatConfiguration(configuration.getList(SUPPORTED));
  }
}
