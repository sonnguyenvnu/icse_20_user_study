private int getFieldCost(String key){
switch (key) {
case "first_name":
case "first_name_native":
    return 20;
case "middle_name":
case "middle_name_native":
  return 21;
case "last_name":
case "last_name_native":
return 22;
case "birth_date":
return 23;
case "gender":
return 24;
case "country_code":
return 25;
case "residence_country_code":
return 26;
case "document_no":
return 27;
case "expiry_date":
return 28;
case "street_line1":
return 29;
case "street_line2":
return 30;
case "post_code":
return 31;
case "city":
return 32;
case "state":
return 33;
}
return 100;
}
