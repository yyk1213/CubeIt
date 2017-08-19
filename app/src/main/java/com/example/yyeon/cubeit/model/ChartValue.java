package com.example.yyeon.cubeit.model;

/**
 * Created by dongseok on 2017. 8. 19..
 */

public class ChartValue {
    private int weekInt;
    private int oneMonthInt;
    private int totalInt;

    public ChartValue() {
    }

    public ChartValue(int weekInt, int oneMonthInt, int totalInt) {
        this.weekInt = weekInt;
        this.oneMonthInt = oneMonthInt;
        this.totalInt = totalInt;
    }

    public int getValue(int mode){
        if(mode == 0){
            return weekInt;
        }else if( mode == 1){
            return oneMonthInt;
        }else{
            return totalInt;
        }
    }

    public int getWeekInt() {
        return weekInt;
    }

    public void setWeekInt(int weekInt) {
        this.weekInt = weekInt;
    }

    public int getOneMonthInt() {
        return oneMonthInt;
    }

    public void setOneMonthInt(int oneMonthInt) {
        this.oneMonthInt = oneMonthInt;
    }

    public int getTotalInt() {
        return totalInt;
    }

    public void setTotalInt(int totalInt) {
        this.totalInt = totalInt;
    }
}
