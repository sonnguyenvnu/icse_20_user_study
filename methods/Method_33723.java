private static int getDefaultPic(int type){
switch (type) {
case 0:
    return R.drawable.img_default_movie;
case 1:
  return R.drawable.img_default_meizi;
case 2:
return R.drawable.img_default_book;
case 3:
return R.drawable.shape_bg_loading;
default :
break;
}
return R.drawable.img_default_meizi;
}
