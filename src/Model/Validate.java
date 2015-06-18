/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Narmada
 */
public class Validate {

    public String[] checkID(String NICNumber) {
        String year, Month, Date, Gender, secPart, lastChar, DoB;
        int YoB, DoD;

        year = NICNumber.substring(0, 2);
        YoB = 1900 + Integer.parseInt(year);
        secPart = NICNumber.substring(2, 5);

        lastChar = NICNumber.substring(9);
        DoD = Integer.parseInt(secPart);

        if (this.isLeapYear(YoB)) {
            if (DoD > 0 && DoD < 367) {
                Gender = "MALE";
                DoB = YoB + "/" + this.getDate(DoD, YoB);

            } else if (DoD > 500 && DoD < 867) {
                Gender = "FEMALE";
                DoB = YoB + "/" + this.getDate(DoD - 500, YoB);
            } else {
                Gender = "ERROR";
                DoB = "0";
            }
        } else {
            if (DoD > 0 && DoD < 366) {
                Gender = "MALE";
                DoB = YoB + "/" + this.getDate(DoD, YoB);
            } else if (DoD > 500 && DoD < 866) {
                Gender = "FEMALE";
                DoB = YoB + "/" + this.getDate(DoD - 500, YoB);
            } else {
                Gender = "ERROR";
                DoB = "0";
            }
        }

        String[] details = new String[3];
        details[0] = Gender;
        details[1] = DoB;
        details[2] = isVoter(lastChar);
        return details;
    }

    private boolean isLeapYear(int year) {
        if ((year % 4) == 0) {
            if ((year % 100) == 0) {
                return (year % 400) == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    private String isVoter(String lastChar) {
        if (lastChar.equalsIgnoreCase("v")) {
            return "YES";
        } else {
            return "NO";
        }
    }

    private String getDate(int date, int YoB) {
        String dateOfBirth;
        int oldDate = date;
        int i = 1;
        while (date > 0) {
            oldDate = date;
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                date = date - 31;
            } else if (i == 2) {
                if (isLeapYear(YoB)) {
                    date = date - 29;
                } else {
                    date = date - 29;// acording to PRD we dontwaont to bother about leapYear
                    //becouse they getas 29 days for Frbuary all year have 29 day
                }

            } else {
                date = date - 30;
            }
            i++;

        }
        dateOfBirth = (i - 1) + "/" + oldDate;

        return dateOfBirth;
    }

}
