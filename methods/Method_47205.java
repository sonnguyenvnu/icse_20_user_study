/** 
 * This creates a channel (API >= 26) or applies the correct metadata to a notification (API < 26)
 */
public static void setMetadata(Context context,NotificationCompat.Builder notification,int type){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
switch (type) {
case TYPE_NORMAL:
      createNormalChannel(context);
    break;
case TYPE_FTP:
  createFtpChannel(context);
break;
default :
throw new IllegalArgumentException("Unrecognized type:" + type);
}
}
 else {
switch (type) {
case TYPE_NORMAL:
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
notification.setCategory(Notification.CATEGORY_SERVICE);
}
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
notification.setPriority(Notification.PRIORITY_MIN);
}
break;
case TYPE_FTP:
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
notification.setCategory(Notification.CATEGORY_SERVICE);
notification.setVisibility(Notification.VISIBILITY_PUBLIC);
}
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
notification.setPriority(Notification.PRIORITY_MAX);
}
break;
default :
throw new IllegalArgumentException("Unrecognized type:" + type);
}
}
}
