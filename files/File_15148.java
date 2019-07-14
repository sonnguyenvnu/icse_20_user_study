package zuo.biao.library.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.model.City;
import zuo.biao.library.util.StringUtil;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;

/**地区管�?�类
 * @author Lemon
 * @use CityDB.getInstance(...).xxMethod(...)
 */
public class CityDB {
    public static final String CITY_DB_NAME = "city.db";
    private static final String CITY_TABLE_NAME = "city";
    private SQLiteDatabase db;

    public CityDB(Context context, String path) {
        db = context.openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
    }
    
	private static CityDB cityDB;
	public static synchronized CityDB getInstance(Context context, String packageName) {
		if (cityDB == null) {
			cityDB = openCityDB(context, packageName);
		}
		return cityDB;
	}
    
    private static CityDB openCityDB(Context context, String packageName) {
        String path = "/data"
                + Environment.getDataDirectory().getAbsolutePath()
                + File.separator + packageName + File.separator
                + CityDB.CITY_DB_NAME;
        File db = new File(path);
        if (!db.exists()) {
            try {
                InputStream is = context.getAssets().open("city.db");
                FileOutputStream fos = new FileOutputStream(db);
                int len = -1;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    fos.flush();
                }
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        return new CityDB(context, path);
    }
	

    public List<City> getAllCity() {
        List<City> list = new ArrayList<City>();
        Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME, null);
        while (c.moveToNext()) {
            String province = c.getString(c.getColumnIndex("province"));
            String city = c.getString(c.getColumnIndex("city"));
            Double latitude = c.getDouble(c.getColumnIndex("latitude"));
            Double longitude = c.getDouble(c.getColumnIndex("longitude"));
            City item = new City(province, city, latitude, longitude);
            list.add(item);
        }
        return list;
    }


    public List<String> getAllProvince() {

        List<String> list = new ArrayList<String>();

        Cursor c = db.rawQuery("SELECT distinct province from " + CITY_TABLE_NAME, null);
        while (c.moveToNext()) {
            String province = c.getString(c.getColumnIndex("province"));
            list.add(province);
        }
        return list;
    }


    /**
     * 拿到�?的所有 地级市
     *
     * @return
     */
    public List<String> getProvinceAllCity(String province) {
    	province = StringUtil.getTrimedString(province);
    	if (province.length() <= 0) {
			return null;
		}

        List<String> list = new ArrayList<String>();

        Cursor c = db.rawQuery("SELECT distinct city from " + CITY_TABLE_NAME + " where province = ? ", new String[]{province});
        while (c.moveToNext()) {
            String city = c.getString(c.getColumnIndex("city"));
            list.add(city);
        }
        return list;
    }

    /**
     * 拿到所有的 县或区
     *
     * @return
     */
    public List<String> getAllCountry(String province, String city) {
    	province = StringUtil.getTrimedString(province);
    	if (province.length() <= 0) {
			return null;
		}
    	city = StringUtil.getTrimedString(city);
    	if (city.length() <= 0) {
    		return null;
    	}

        List<String> list = new ArrayList<String>();

        Cursor c = db.rawQuery("SELECT country from " + CITY_TABLE_NAME + " where province = ? and city = ?", new String[]{province, city});
        while (c.moveToNext()) {
            String country = c.getString(c.getColumnIndex("country"));
            list.add(country);
        }
        return list;
    }

    public City getCity(String city) {
        if (TextUtils.isEmpty(city))
            return null;
        City item = getCityInfo(parseName(city));
        if (item == null) {
            item = getCityInfo(city);
        }
        return item;
    }

    /**
     * 去掉市或县�?�索
     *
     * @param city
     * @return
     */
    private String parseName(String city) {
        if (city.contains("市")) {// 如果为空就去掉市字�?试试
            String subStr[] = city.split("市");
            city = subStr[0];
        } else if (city.contains("县")) {// 或者去掉县字�?试试
            String subStr[] = city.split("县");
            city = subStr[0];
        }
        return city;
    }

    private City getCityInfo(String city) {
        Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME
                + " where city=?", new String[]{city});
        if (c.moveToFirst()) {
            String province = c.getString(c.getColumnIndex("province"));
            String name = c.getString(c.getColumnIndex("city"));
            Double latitude = c.getDouble(c.getColumnIndex("latitude"));
            Double longitude = c.getDouble(c.getColumnIndex("longitude"));

            City item = new City(province, name, latitude, longitude);
            return item;
        }
        return null;
    }


    /**
     * 查询附近的城市
     * 画正方形
     */
    public List<String> getNearbyCityList(String sCity) {
        City city = getCity(sCity);
        List<String> nearbyCitysList = new ArrayList<String>();
        //根�?�常跑地信�?�画正方形地域
        double lat = city.getLatitude() + 0.9;
        double lon = city.getLongitude() + 0.9;
        double lat1 = city.getLatitude() - 0.9;
        double lon1 = city.getLongitude() - 0.9;

        Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME + " WHERE LATITUDE < " + lat + " AND LATITUDE > " + lat1 +
                " AND LONGITUDE <" + lon + " AND LONGITUDE > " + lon1, null);
        while (c.moveToNext()) {
            String nearbyCity = c.getString(c.getColumnIndex("city"));
            nearbyCitysList.add(nearbyCity);
        }
        return nearbyCitysList;
    }



}
