public static void adjustregularpath(ST_path P,int fb,int lb){
  ENTERING("88xrlzjovkxcnay9b2y5zyiid","adjustregularpath");
  try {
    ST_boxf bp1, bp2;
    int i, x;
    for (i=fb - 1; i < lb + 1; i++) {
      bp1=(ST_boxf)P.boxes[i];
      if ((i - fb) % 2 == 0) {
        if (bp1.LL.x >= bp1.UR.x) {
          x=(int)((bp1.LL.x + bp1.UR.x) / 2);
          bp1.LL.setDouble("x",x - 8);
          bp1.UR.setDouble("x",x + 8);
        }
      }
 else {
        if (bp1.LL.x + 16 > bp1.UR.x) {
          x=(int)((bp1.LL.x + bp1.UR.x) / 2);
          bp1.LL.setDouble("x",x - 8);
          bp1.UR.setDouble("x",x + 8);
        }
      }
    }
    for (i=0; i < P.nbox - 1; i++) {
      bp1=(ST_boxf)P.boxes[i];
      bp2=(ST_boxf)P.boxes[i + 1];
      if (i >= fb && i <= lb && (i - fb) % 2 == 0) {
        if (bp1.LL.x + 16 > bp2.UR.x)         bp2.UR.setDouble("x",bp1.LL.x + 16);
        if (bp1.UR.x - 16 < bp2.LL.x)         bp2.LL.setDouble("x",bp1.UR.x - 16);
      }
 else       if (i + 1 >= fb && i < lb && (i + 1 - fb) % 2 == 0) {
        if (bp1.LL.x + 16 > bp2.UR.x)         bp1.LL.setDouble("x",bp2.UR.x - 16);
        if (bp1.UR.x - 16 < bp2.LL.x)         bp1.UR.setDouble("x",bp2.LL.x + 16);
      }
    }
  }
  finally {
    LEAVING("88xrlzjovkxcnay9b2y5zyiid","adjustregularpath");
  }
}
