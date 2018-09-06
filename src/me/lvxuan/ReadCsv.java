package me.lvxuan;

import com.google.gson.Gson;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import me.lvxuan.bean.City;
import me.lvxuan.bean.District;
import me.lvxuan.bean.Province;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by LvXuan on 2018/09/04
 * 读取Csv文件
 * 读取和风天气的CSV文件，并提取需要的信息转换成 json，并写入json文件
 */
public class ReadCsv {

    private String mCityId = "CN101000000";

    private ArrayList<Province> mProvinceList = new ArrayList<>();
    private ArrayList<City> mCityList;
    private ArrayList<District> mDistrictList;


    /**
     * @param csvFilePath       CSV 的路径
     * @param saveJsonFilePath  即将保存josn 文件的路径
     */
    public void read(@NotNull String csvFilePath, @Nullable String saveJsonFilePath) {
        if (csvFilePath == null || csvFilePath.isEmpty()) {
            new Throwable("请输入和风天气中国地区CSV文件路径").printStackTrace();
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));//换成你的文件名
            reader.readLine();
            reader.readLine();//第 1、2 行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while ((line = reader.readLine()) != null) {
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

                /** CN101 010100   ——  */
                String cityName = item[9];
                String districtName = item[2];
                String cityPinYin = item[1];

                int provinceId = Integer.valueOf(item[0].substring(5, 7));  //省
//                int cityId = Integer.valueOf(item[0].substring(7, 9));  //市
//                int districtId = Integer.valueOf(item[0].substring(9, 11));  // 区

                //省
                if (provinceId > Integer.valueOf(mCityId.substring(5, 7))) {
                    String provinceName = item[7];
                    String pinYin = item[6];
                    System.out.println(item[0] + "  " + provinceName + "  -------------------------");
                    mCityList = new ArrayList<>();
                    Province province = new Province(provinceName, pinYin, mCityList);
                    mProvinceList.add(province);
                }

                //市
                if (provinceId > 4) {
                    if (Integer.valueOf(item[0].substring(5, 9)) > Integer.valueOf(mCityId.substring(5, 9))) {
                        System.out.println("-------- " + cityName);
                        mDistrictList = new ArrayList<>();
                        mCityList.add(new City(cityName, cityPinYin, mDistrictList));
                    }

                } else {
                    // 4 个直辖市
                    if (provinceId > Integer.valueOf(mCityId.substring(5, 7))) {
                        mDistrictList = new ArrayList<>();
                        mCityList.add(new City(cityName, cityPinYin, mDistrictList));
                        System.out.println("-------- " + cityName);

                    }
                }

                //地区编号
                ArrayList<String> list = new ArrayList();
                for (int i = 12; i < item.length; i++) {
                    if (item[i].startsWith("\"")) {
                        item[i] = item[i].substring(1);
                    } else if (item[i].endsWith("\"")) {
                        item[i] = item[i].substring(0, item[i].length() - 1);
                    }
                    list.add(item[i]);

                }

                //地区
                District district = new District();
                district.setAdCode(list);
                district.setCityId(item[0]);
                district.setLatitude(item[10]);
                district.setLongitude(item[11]);
                district.setName(districtName);
                district.setPinyin(cityPinYin);
                mDistrictList.add(district);

                System.out.println(districtName + "\t\t" + list.toString());

                mCityId = item[0];
            }

            saveJsonFile(saveJsonFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveJsonFile(@Nullable String path) {
        if (path == null || path.isEmpty()) {
            path = "D:/area_json.json";
        }
//        if (!path.contains(".")) {
//            path += ".json";
//        }

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            String data = new Gson().toJson(mProvinceList);
            byte[] bytes = data.getBytes();
            bos.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
