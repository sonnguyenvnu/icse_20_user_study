/** 
 * ????????????? invoke() ??
 * @param value
 * @param context
 * @throws Exception
 */
@Override public void invoke(Student value,Context context) throws Exception {
  ps.setInt(1,value.getId());
  ps.setString(2,value.getName());
  ps.setString(3,value.getPassword());
  ps.setInt(4,value.getAge());
  ps.executeUpdate();
}
