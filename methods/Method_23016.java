static private float parseZoom(){
  if (Preferences.getBoolean("editor.zoom.auto")) {
    float newZoom=Platform.getSystemDPI() / 96f;
    String percentSel=((int)(newZoom * 100)) + "%";
    Preferences.set("editor.zoom",percentSel);
    return newZoom;
  }
 else {
    String zoomSel=Preferences.get("editor.zoom");
    if (zoomOptions.hasValue(zoomSel)) {
      zoomSel=zoomSel.substring(0,zoomSel.length() - 1);
      return PApplet.parseInt(zoomSel,100) / 100f;
    }
 else {
      Preferences.set("editor.zoom","100%");
      return 1;
    }
  }
}
