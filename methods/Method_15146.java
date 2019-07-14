public static synchronized CityDB getInstance(Context context,String packageName){
  if (cityDB == null) {
    cityDB=openCityDB(context,packageName);
  }
  return cityDB;
}
