private static void test4(){
  Stream.of(new BigDecimal("1.2"),new BigDecimal("3.7")).mapToDouble(BigDecimal::doubleValue).average().ifPresent(System.out::println);
}
