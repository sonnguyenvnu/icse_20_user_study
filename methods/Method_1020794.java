/** 
 * <p> ??????? </p>
 */
public static String scanner(String tip){
  Scanner scanner=new Scanner(System.in);
  StringBuilder help=new StringBuilder();
  help.append("???" + tip + "?");
  System.out.println(help.toString());
  if (scanner.hasNext()) {
    String ipt=scanner.next();
    if (StringUtils.isNotEmpty(ipt)) {
      return ipt;
    }
  }
  throw new MybatisPlusException("??????" + tip + "?");
}
