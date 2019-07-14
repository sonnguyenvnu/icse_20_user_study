/** 
 * @deprecated Use {@link ParametersBuilder#setTunnelingAudioSessionId(int)}. 
 */
@Deprecated public void setTunnelingAudioSessionId(int tunnelingAudioSessionId){
  setParameters(buildUponParameters().setTunnelingAudioSessionId(tunnelingAudioSessionId));
}
