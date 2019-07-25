public void play(@RawRes int resId){
  boolean isTouchSoundsEnabled=Settings.System.getInt(context.getContentResolver(),Settings.System.SOUND_EFFECTS_ENABLED,1) != 0;
  if (isTouchSoundsEnabled) {
    if (soundIds.get(resId) == 0)     prepare(resId);
    sounds.play(soundIds.get(resId),1,1,1,0,1);
  }
}
