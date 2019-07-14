public static int notificationDurationMillis(@NonNull String prefValue){
  if (!InputHelper.isEmpty(prefValue)) {
switch (prefValue) {
case "1":
      return 60;
case "5":
    return 5 * 60;
case "10":
  return 10 * 60;
case "20":
return 20 * 60;
case "30":
return 30 * 60;
case "60":
return 60 * 60;
case "120":
return (60 * 2) * 60;
case "180":
return (60 * 3) * 60;
}
}
return -1;
}
