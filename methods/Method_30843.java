@Override public CharSequence getSummary(){
  CharSequence summary=super.getSummary();
  if (!TextUtils.isEmpty(summary)) {
    Uri ringtoneUri=getRingtoneUri();
    String ringtoneTitle="";
    if (ringtoneUri != null) {
      Context context=getContext();
      Ringtone ringtone=RingtoneManager.getRingtone(context,ringtoneUri);
      if (ringtone != null) {
        ringtoneTitle=ringtone.getTitle(context);
      }
    }
    return String.format(summary.toString(),ringtoneTitle);
  }
 else {
    return summary;
  }
}
