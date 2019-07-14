/** 
 * @since 1.2.57
 */
public final void writeAs(Object object,Class type){
  if (object == null) {
    out.writeNull();
    return;
  }
  ObjectSerializer writer=getObjectWriter(type);
  try {
    writer.write(this,object,null,null,0);
  }
 catch (  IOException e) {
    throw new JSONException(e.getMessage(),e);
  }
}
