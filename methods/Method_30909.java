public static int requestAudioFocus(AudioManager audioManager,int focusGain,AudioAttributesCompat attributes,AudioManager.OnAudioFocusChangeListener listener){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    AudioFocusRequest request=new AudioFocusRequest.Builder(focusGain).setAudioAttributes((AudioAttributes)attributes.unwrap()).setOnAudioFocusChangeListener(listener).build();
    sListenerRequestMap.put(listener,request);
    return audioManager.requestAudioFocus(request);
  }
 else {
    return audioManager.requestAudioFocus(listener,attributes.getLegacyStreamType(),focusGain);
  }
}
