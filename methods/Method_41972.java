private void setupFAB(){
  fab.setImageDrawable(new IconicsDrawable(getApplicationContext()).icon(GoogleMaterial.Icon.gmd_camera_alt).color(Color.WHITE));
  fab.setOnClickListener(v -> startActivity(new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA)));
}
