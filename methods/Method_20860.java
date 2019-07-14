public static String friendBackingActivityTitle(final @NonNull Context context,final @NonNull String friendName,final long categoryId,final @NonNull KSString ksString){
  final String str;
switch ((int)categoryId) {
case 1:
    str=ksString.format(context.getString(R.string.activity_friend_backed_art_project),"friend_name",friendName);
  break;
case 3:
str=ksString.format(context.getString(R.string.activity_friend_backed_comics_project),"friend_name",friendName);
break;
case 26:
str=ksString.format(context.getString(R.string.activity_friend_backed_crafts_project),"friend_name",friendName);
break;
case 6:
str=ksString.format(context.getString(R.string.activity_friend_backed_dance_project),"friend_name",friendName);
break;
case 7:
str=ksString.format(context.getString(R.string.activity_friend_backed_design_project),"friend_name",friendName);
break;
case 9:
str=ksString.format(context.getString(R.string.activity_friend_backed_fashion_project),"friend_name",friendName);
break;
case 11:
str=ksString.format(context.getString(R.string.activity_friend_backed_film_project),"friend_name",friendName);
break;
case 10:
str=ksString.format(context.getString(R.string.activity_friend_backed_food_project),"friend_name",friendName);
break;
case 12:
str=ksString.format(context.getString(R.string.activity_friend_backed_games_project),"friend_name",friendName);
break;
case 13:
str=ksString.format(context.getString(R.string.activity_friend_backed_journalism_project),"friend_name",friendName);
break;
case 14:
str=ksString.format(context.getString(R.string.activity_friend_backed_music_project),"friend_name",friendName);
break;
case 15:
str=ksString.format(context.getString(R.string.activity_friend_backed_photography_project),"friend_name",friendName);
break;
case 18:
str=ksString.format(context.getString(R.string.activity_friend_backed_publishing_project),"friend_name",friendName);
break;
case 16:
str=ksString.format(context.getString(R.string.activity_friend_backed_tech_project),"friend_name",friendName);
break;
case 17:
str=ksString.format(context.getString(R.string.activity_friend_backed_theater_project),"friend_name",friendName);
break;
default :
str="";
}
return str;
}
