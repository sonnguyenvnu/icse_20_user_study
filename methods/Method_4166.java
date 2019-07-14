/** 
 * Registers the receiver, meaning it will notify the listener when audio capability changes occur. The current audio capabilities will be returned. It is important to call {@link #unregister} when the receiver is no longer required.
 * @return The current audio capabilities for the device.
 */
@SuppressWarnings("InlinedApi") public AudioCapabilities register(){
  Intent stickyIntent=null;
  if (receiver != null) {
    IntentFilter intentFilter=new IntentFilter(AudioManager.ACTION_HDMI_AUDIO_PLUG);
    if (handler != null) {
      stickyIntent=context.registerReceiver(receiver,intentFilter,null,handler);
    }
 else {
      stickyIntent=context.registerReceiver(receiver,intentFilter);
    }
  }
  audioCapabilities=AudioCapabilities.getCapabilities(stickyIntent);
  return audioCapabilities;
}
