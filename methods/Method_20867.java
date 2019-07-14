/** 
 * Set toggle state without any animations.
 */
public static void setCheckedWithoutAnimation(final @NonNull SwitchCompat switchCompat,final boolean checked){
  switchCompat.setVisibility(View.INVISIBLE);
  switchCompat.setChecked(checked);
  switchCompat.setVisibility(View.VISIBLE);
}
