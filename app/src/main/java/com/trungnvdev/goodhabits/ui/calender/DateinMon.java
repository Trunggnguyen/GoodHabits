package com.trungnvdev.goodhabits.ui.calender;

public class DateinMon {
    String daysInMon;
    boolean check;
    boolean datenow;
    boolean start;
    boolean end;

    public DateinMon(String daysInMon, boolean check, boolean datenow, boolean start, boolean end) {
        this.daysInMon = daysInMon;
        this.check = check;
        this.datenow = datenow;
        this.start = start;
        this.end = end;
    }

    public boolean isDatenow() {
        return datenow;
    }

    public void setDatenow(boolean datenow) {
        this.datenow = datenow;
    }

    public String getDaysInMon() {
        return daysInMon;
    }

    public void setDaysInMon(String daysInMon) {
        this.daysInMon = daysInMon;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
