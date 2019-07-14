private ArrayList<LocationController.SharingLocationInfo> getInfos(){
  ArrayList<LocationController.SharingLocationInfo> infos=new ArrayList<>();
  for (int a=0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
    ArrayList<LocationController.SharingLocationInfo> arrayList=LocationController.getInstance(a).sharingLocationsUI;
    if (!arrayList.isEmpty()) {
      infos.addAll(arrayList);
    }
  }
  return infos;
}
