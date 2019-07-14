@Override public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  respondJSON(response,new FunctionsAndDistancesResponse());
}
