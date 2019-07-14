public String getIntegralNames(String path){
  String newPath="";
switch (Integer.parseInt(path)) {
case 0:
    newPath=mainActivity.getString(R.string.images);
  break;
case 1:
newPath=mainActivity.getString(R.string.videos);
break;
case 2:
newPath=mainActivity.getString(R.string.audio);
break;
case 3:
newPath=mainActivity.getString(R.string.documents);
break;
case 4:
newPath=mainActivity.getString(R.string.apks);
break;
case 5:
newPath=mainActivity.getString(R.string.quick);
break;
case 6:
newPath=mainActivity.getString(R.string.recent);
break;
}
return newPath;
}
