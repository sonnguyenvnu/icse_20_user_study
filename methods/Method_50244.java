/** 
 * ??????????--????9?? setType(SelectRXType.TYPE_IMAGE,SelectRXType.TYPE_SELECT_RADIO); setType(SelectRXType.TYPE_VIDEO,SelectRXType.TYPE_SELECT_RADIO);
 * @param type ??????
 * @param mt   ?????? .????9??
 */
public RxGalleryFinalApi setType(int type,int mt){
switch (type) {
case SelectRXType.TYPE_IMAGE:
    rxGalleryFinal.image();
  break;
case SelectRXType.TYPE_VIDEO:
rxGalleryFinal.video();
break;
default :
Logger.e("open type is error!!!");
break;
}
switch (mt) {
case SelectRXType.TYPE_SELECT_RADIO:
rxGalleryFinal.radio();
break;
case SelectRXType.TYPE_SELECT_MULTI:
rxGalleryFinal.multiple();
rxGalleryFinal.maxSize(9);
break;
default :
Logger.e("open mt is error!!!");
break;
}
return mRxApi;
}
