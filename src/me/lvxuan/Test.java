package me.lvxuan;

public class Test {
    private static final String PATH = "C:\\Users\\Administrator\\Documents\\china-city-list.csv";

    public static void main(String[] args){
        ReadCsv readCsv = new ReadCsv();
        readCsv.read(PATH, null);
    }
}
