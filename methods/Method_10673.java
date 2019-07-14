/** 
 * ????? <p>?????  {@code <uses-permission android:name="android.permission.EXPAND_STATUS_BAR"/>}</p>
 * @param context        ???
 * @param isSettingPanel {@code true}: ????<br> {@code false}: ????
 */
public static void showNotificationBar(Context context,boolean isSettingPanel){
  String methodName=(Build.VERSION.SDK_INT <= 16) ? "expand" : (isSettingPanel ? "expandSettingsPanel" : "expandNotificationsPanel");
  invokePanels(context,methodName);
}
