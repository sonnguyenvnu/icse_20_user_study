public static boolean APPROXEQPT(ST_pointf p,ST_pointf q,double tol){
  return (DIST2((p),(q)) < SQR(tol));
}
