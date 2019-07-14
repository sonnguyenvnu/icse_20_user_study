@SuppressLint("InlinedApi") static AudioCapabilities getCapabilities(@Nullable Intent intent){
  if (intent == null || intent.getIntExtra(AudioManager.EXTRA_AUDIO_PLUG_STATE,0) == 0) {
    return DEFAULT_AUDIO_CAPABILITIES;
  }
  return new AudioCapabilities(intent.getIntArrayExtra(AudioManager.EXTRA_ENCODINGS),intent.getIntExtra(AudioManager.EXTRA_MAX_CHANNEL_COUNT,DEFAULT_MAX_CHANNEL_COUNT));
}
