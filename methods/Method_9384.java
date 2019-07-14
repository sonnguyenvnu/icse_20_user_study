private void setFieldValues(HashMap<String,String> values,EditTextBoldCursor editText,String key){
  String value;
  if ((value=values.get(key)) != null) {
switch (key) {
case "country_code":
{
        currentCitizeship=value;
        String country=languageMap.get(currentCitizeship);
        if (country != null) {
          editText.setText(country);
        }
        break;
      }
case "residence_country_code":
{
      currentResidence=value;
      String country=languageMap.get(currentResidence);
      if (country != null) {
        editText.setText(country);
      }
      break;
    }
case "gender":
  if ("male".equals(value)) {
    currentGender=value;
    editText.setText(LocaleController.getString("PassportMale",R.string.PassportMale));
  }
 else   if ("female".equals(value)) {
    currentGender=value;
    editText.setText(LocaleController.getString("PassportFemale",R.string.PassportFemale));
  }
break;
case "expiry_date":
boolean ok=false;
if (!TextUtils.isEmpty(value)) {
String[] args=value.split("\\.");
if (args.length == 3) {
currentExpireDate[0]=Utilities.parseInt(args[2]);
currentExpireDate[1]=Utilities.parseInt(args[1]);
currentExpireDate[2]=Utilities.parseInt(args[0]);
editText.setText(value);
ok=true;
}
}
if (!ok) {
currentExpireDate[0]=currentExpireDate[1]=currentExpireDate[2]=0;
editText.setText(LocaleController.getString("PassportNoExpireDate",R.string.PassportNoExpireDate));
}
break;
default :
editText.setText(value);
break;
}
}
if (fieldsErrors != null && (value=fieldsErrors.get(key)) != null) {
editText.setErrorText(value);
errorsValues.put(key,editText.getText().toString());
}
 else if (documentsErrors != null && (value=documentsErrors.get(key)) != null) {
editText.setErrorText(value);
errorsValues.put(key,editText.getText().toString());
}
}
