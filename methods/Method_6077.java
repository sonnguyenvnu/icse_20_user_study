/** 
 * Returns whether the codec is known to implement  {@link MediaCodec#setOutputSurface(Surface)}incorrectly. <p>If true is returned then we fall back to releasing and re-instantiating the codec instead.
 * @param name The name of the codec.
 * @return True if the device is known to implement {@link MediaCodec#setOutputSurface(Surface)}incorrectly.
 */
protected boolean codecNeedsSetOutputSurfaceWorkaround(String name){
  if (name.startsWith("OMX.google")) {
    return false;
  }
synchronized (MediaCodecVideoRenderer.class) {
    if (!evaluatedDeviceNeedsSetOutputSurfaceWorkaround) {
      if (Util.SDK_INT <= 27 && "dangal".equals(Util.DEVICE)) {
        deviceNeedsSetOutputSurfaceWorkaround=true;
      }
 else       if (Util.SDK_INT >= 27) {
      }
 else {
switch (Util.DEVICE) {
case "1601":
case "1713":
case "1714":
case "A10-70F":
case "A1601":
case "A2016a40":
case "A7000-a":
case "A7000plus":
case "A7010a48":
case "A7020a48":
case "AquaPowerM":
case "ASUS_X00AD_2":
case "Aura_Note_2":
case "BLACK-1X":
case "BRAVIA_ATV2":
case "BRAVIA_ATV3_4K":
case "C1":
case "ComioS1":
case "CP8676_I02":
case "CPH1609":
case "CPY83_I00":
case "cv1":
case "cv3":
case "deb":
case "E5643":
case "ELUGA_A3_Pro":
case "ELUGA_Note":
case "ELUGA_Prim":
case "ELUGA_Ray_X":
case "EverStar_S":
case "F3111":
case "F3113":
case "F3116":
case "F3211":
case "F3213":
case "F3215":
case "F3311":
case "flo":
case "fugu":
case "GiONEE_CBL7513":
case "GiONEE_GBL7319":
case "GIONEE_GBL7360":
case "GIONEE_SWW1609":
case "GIONEE_SWW1627":
case "GIONEE_SWW1631":
case "GIONEE_WBL5708":
case "GIONEE_WBL7365":
case "GIONEE_WBL7519":
case "griffin":
case "htc_e56ml_dtul":
case "hwALE-H":
case "HWBLN-H":
case "HWCAM-H":
case "HWVNS-H":
case "HWWAS-H":
case "i9031":
case "iball8735_9806":
case "Infinix-X572":
case "iris60":
case "itel_S41":
case "j2xlteins":
case "JGZ":
case "K50a40":
case "kate":
case "le_x6":
case "LS-5017":
case "M5c":
case "manning":
case "marino_f":
case "MEIZU_M5":
case "mh":
case "mido":
case "MX6":
case "namath":
case "nicklaus_f":
case "NX541J":
case "NX573J":
case "OnePlus5T":
case "p212":
case "P681":
case "P85":
case "panell_d":
case "panell_dl":
case "panell_ds":
case "panell_dt":
case "PB2-670M":
case "PGN528":
case "PGN610":
case "PGN611":
case "Phantom6":
case "Pixi4-7_3G":
case "Pixi5-10_4G":
case "PLE":
case "PRO7S":
case "Q350":
case "Q4260":
case "Q427":
case "Q4310":
case "Q5":
case "QM16XE_U":
case "QX1":
case "santoni":
case "Slate_Pro":
case "SVP-DTV15":
case "s905x018":
case "taido_row":
case "TB3-730F":
case "TB3-730X":
case "TB3-850F":
case "TB3-850M":
case "tcl_eu":
case "V1":
case "V23GB":
case "V5":
case "vernee_M5":
case "watson":
case "whyred":
case "woods_f":
case "woods_fn":
case "X3_HK":
case "XE2X":
case "XT1663":
case "Z12_PRO":
case "Z80":
          deviceNeedsSetOutputSurfaceWorkaround=true;
        break;
default :
      break;
  }
switch (Util.MODEL) {
case "AFTA":
case "AFTN":
    deviceNeedsSetOutputSurfaceWorkaround=true;
  break;
default :
break;
}
}
evaluatedDeviceNeedsSetOutputSurfaceWorkaround=true;
}
}
return deviceNeedsSetOutputSurfaceWorkaround;
}
