public static AlertDialog getDetailsDialog(final ThemedActivity activity,final Media f){
  AlertDialog.Builder detailsDialogBuilder=new AlertDialog.Builder(activity,activity.getDialogStyle());
  MetadataHelper mdhelper=new MetadataHelper();
  MediaDetailsMap<String,String> mainDetails=mdhelper.getMainDetails(activity,f);
  final View dialogLayout=activity.getLayoutInflater().inflate(org.horaapps.leafpic.R.layout.dialog_media_detail,null);
  ImageView imgMap=dialogLayout.findViewById(R.id.photo_map);
  dialogLayout.findViewById(org.horaapps.leafpic.R.id.details_title).setBackgroundColor(activity.getPrimaryColor());
  ((CardView)dialogLayout.findViewById(org.horaapps.leafpic.R.id.photo_details_card)).setCardBackgroundColor(activity.getCardBackgroundColor());
  final GeoLocation location;
  if ((location=f.getGeoLocation()) != null) {
    StaticMapProvider staticMapProvider=StaticMapProvider.fromValue(Hawk.get(activity.getString(R.string.preference_map_provider),StaticMapProvider.GOOGLE_MAPS.getValue()));
    Glide.with(activity.getApplicationContext()).load(staticMapProvider.getUrl(location)).into(imgMap);
    imgMap.setOnClickListener(new View.OnClickListener(){
      public void onClick(      View v){
        try {
          activity.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(String.format(Locale.ENGLISH,"geo:%f,%f?z=%d",location.getLatitude(),location.getLongitude(),17))));
        }
 catch (        ActivityNotFoundException e) {
          Toast.makeText(activity,R.string.no_app_to_perform,Toast.LENGTH_SHORT).show();
        }
      }
    }
);
    imgMap.setVisibility(View.VISIBLE);
    dialogLayout.findViewById(org.horaapps.leafpic.R.id.details_title).setVisibility(View.GONE);
  }
 else   imgMap.setVisibility(View.GONE);
  final TextView showMoreText=dialogLayout.findViewById(R.id.details_showmore);
  showMoreText.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View view){
      showMoreDetails(dialogLayout,activity,f);
      showMoreText.setVisibility(View.GONE);
    }
  }
);
  detailsDialogBuilder.setView(dialogLayout);
  loadDetails(dialogLayout,activity,mainDetails);
  return detailsDialogBuilder.create();
}
