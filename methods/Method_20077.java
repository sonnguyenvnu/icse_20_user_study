private static String getCategoryName(@FirebaseVisionObject.Category int category){
switch (category) {
case FirebaseVisionObject.CATEGORY_UNKNOWN:
    return "Unknown";
case FirebaseVisionObject.CATEGORY_HOME_GOOD:
  return "Home good";
case FirebaseVisionObject.CATEGORY_FASHION_GOOD:
return "Fashion good";
case FirebaseVisionObject.CATEGORY_PLACE:
return "Place";
case FirebaseVisionObject.CATEGORY_PLANT:
return "Plant";
case FirebaseVisionObject.CATEGORY_FOOD:
return "Food";
default :
}
return "";
}
