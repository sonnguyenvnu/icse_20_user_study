private ArrayList<String[]> getDbTableDetails(Cursor c){
  ArrayList<String[]> result=new ArrayList<>();
  int j=0;
  for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    if (!isCancelled()) {
      j++;
      publishProgress(j);
      String[] temp=new String[c.getColumnCount()];
      for (int i=0; i < temp.length; i++) {
        int dataType=c.getType(i);
switch (dataType) {
case 0:
          temp[i]=null;
        break;
case 1:
      temp[i]=String.valueOf(c.getInt(i));
    break;
case 2:
  temp[i]=String.valueOf(c.getFloat(i));
break;
case 3:
temp[i]=c.getString(i);
break;
case 4:
temp[i]="(BLOB)";
break;
}
}
result.add(temp);
}
 else {
break;
}
}
return result;
}
