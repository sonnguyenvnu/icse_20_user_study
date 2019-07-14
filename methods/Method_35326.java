/** 
 * {@link #KEY_PLAY_MODE}
 */
public static PlayMode lastPlayMode(Context context){
  String playModeName=preferences(context).getString(KEY_PLAY_MODE,null);
  if (playModeName != null) {
    return PlayMode.valueOf(playModeName);
  }
  return PlayMode.getDefault();
}
