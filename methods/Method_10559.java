public List<CityModel> getCities(Context mContext){
  AssetManager assetManager=mContext.getAssets();
  List<CityModel> cityModels=new ArrayList<>();
  try {
    InputStream inputStream=assetManager.open("city_data.xml");
    cityModels=RxPullXml.newInstance().parseXMLWithPull(inputStream,mEdXmlData);
    inputStream.close();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return cityModels;
}
