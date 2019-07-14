/** 
 * ??? <p>?????  {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>}</p>
 * @param crashDir ????????
 */
public static void init(@NonNull final File crashDir){
  init(crashDir.getAbsolutePath());
}
