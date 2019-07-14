private void updateInfoText(){
  String facingValue=cameraView.getFacing() == CameraKit.FACING_BACK ? "BACK" : "FRONT";
  facingText.setText(Html.fromHtml("<b>Facing:</b> " + facingValue));
  String flashValue="OFF";
switch (cameraView.getFlash()) {
case CameraKit.FLASH_OFF:
{
      flashValue="OFF";
      break;
    }
case CameraKit.FLASH_ON:
{
    flashValue="ON";
    break;
  }
case CameraKit.FLASH_AUTO:
{
  flashValue="AUTO";
  break;
}
case CameraKit.FLASH_TORCH:
{
flashValue="TORCH";
break;
}
}
flashText.setText(Html.fromHtml("<b>Flash:</b> " + flashValue));
CameraSize previewSize=cameraView.getPreviewResolution();
if (previewSize != null) {
previewSizeText.setText(Html.fromHtml(String.format("<b>Preview Resolution:</b> %d x %d",previewSize.getWidth(),previewSize.getHeight())));
}
CameraSize photoSize=cameraView.getPhotoResolution();
if (photoSize != null) {
photoSizeText.setText(Html.fromHtml(String.format("<b>Photo Resolution:</b> %d x %d",photoSize.getWidth(),photoSize.getHeight())));
}
}
