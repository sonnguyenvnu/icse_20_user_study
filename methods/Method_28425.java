private void restoreData(Intent data){
  StringBuilder json=new StringBuilder();
  try {
    try (InputStream inputStream=getContext().getContentResolver().openInputStream(data.getData())){
      if (inputStream != null) {
        try (BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream))){
          String line;
          while ((line=reader.readLine()) != null) {
            json.append(line);
          }
        }
       }
    }
   }
 catch (  IOException e) {
    Toasty.error(App.getInstance(),getString(R.string.error)).show();
  }
  if (!InputHelper.isEmpty(json)) {
    try {
      Gson gson=new Gson();
      Type typeOfHashMap=new TypeToken<Map<String,?>>(){
      }
.getType();
      Map<String,?> savedPref=gson.fromJson(json.toString(),typeOfHashMap);
      if (savedPref != null && !savedPref.isEmpty()) {
        for (        Map.Entry<String,?> stringEntry : savedPref.entrySet()) {
          PrefHelper.set(stringEntry.getKey(),stringEntry.getValue());
        }
      }
      callback.onThemeChanged();
    }
 catch (    Exception ignored) {
      Toasty.error(App.getInstance(),getString(R.string.error),Toast.LENGTH_SHORT).show();
    }
  }
}
