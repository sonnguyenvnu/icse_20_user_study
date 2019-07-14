/** 
 * We're parsing a line returned from a stdout of shell.
 * @param line must be the line returned from a 'ls' command
 */
public static HybridFileParcelable parseName(String line){
  boolean linked=false;
  StringBuilder name=new StringBuilder();
  StringBuilder link=new StringBuilder();
  String size="-1";
  String date="";
  String[] array=line.split(" ");
  if (array.length < 6)   return null;
  for (  String anArray : array) {
    if (anArray.contains("->") && array[0].startsWith("l")) {
      linked=true;
    }
  }
  int p=getColonPosition(array);
  if (p != -1) {
    date=array[p - 1] + " | " + array[p];
    size=array[p - 2];
  }
  if (!linked) {
    for (int i=p + 1; i < array.length; i++) {
      name.append(" ").append(array[i]);
    }
    name=new StringBuilder(name.toString().trim());
  }
 else {
    int q=getLinkPosition(array);
    for (int i=p + 1; i < q; i++) {
      name.append(" ").append(array[i]);
    }
    name=new StringBuilder(name.toString().trim());
    for (int i=q + 1; i < array.length; i++) {
      link.append(" ").append(array[i]);
    }
  }
  long Size=(size == null || size.trim().length() == 0) ? -1 : Long.parseLong(size);
  if (date.trim().length() > 0) {
    ParsePosition pos=new ParsePosition(0);
    SimpleDateFormat simpledateformat=new SimpleDateFormat("yyyy-MM-dd | HH:mm");
    Date stringDate=simpledateformat.parse(date,pos);
    HybridFileParcelable baseFile=new HybridFileParcelable(name.toString(),array[0],stringDate.getTime(),Size,true);
    baseFile.setLink(link.toString());
    return baseFile;
  }
 else {
    HybridFileParcelable baseFile=new HybridFileParcelable(name.toString(),array[0],new File("/").lastModified(),Size,true);
    baseFile.setLink(link.toString());
    return baseFile;
  }
}
