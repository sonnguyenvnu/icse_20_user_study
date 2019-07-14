public void init(String countryCode){
  InputStream stream=null;
  ByteArrayOutputStream bos=null;
  try {
    stream=ApplicationLoader.applicationContext.getAssets().open("PhoneFormats.dat");
    bos=new ByteArrayOutputStream();
    byte[] buf=new byte[1024];
    int len;
    while ((len=stream.read(buf,0,1024)) != -1) {
      bos.write(buf,0,len);
    }
    data=bos.toByteArray();
    buffer=ByteBuffer.wrap(data);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
  }
 catch (  Exception e) {
    e.printStackTrace();
    return;
  }
 finally {
    try {
      if (bos != null) {
        bos.close();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    try {
      if (stream != null) {
        stream.close();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  if (countryCode != null && countryCode.length() != 0) {
    defaultCountry=countryCode;
  }
 else {
    Locale loc=Locale.getDefault();
    defaultCountry=loc.getCountry().toLowerCase();
  }
  callingCodeOffsets=new HashMap<>(255);
  callingCodeCountries=new HashMap<>(255);
  callingCodeData=new HashMap<>(10);
  countryCallingCode=new HashMap<>(255);
  parseDataHeader();
  initialzed=true;
}
