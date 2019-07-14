@Flash public int toggleFlash(){
switch (mFlash) {
case FLASH_OFF:
    setFlash(FLASH_ON);
  break;
case FLASH_ON:
setFlash(FLASH_AUTO);
break;
case FLASH_AUTO:
case FLASH_TORCH:
setFlash(FLASH_OFF);
break;
}
return mFlash;
}
