package com.sir.taxesejourV1.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil {

    public static final String paternYYYYMMDDmmhhSS = "yyyy-MM-dd hh:mm:ss";
    public static final String paternYYYYMMDD = "yyyy-MM-dd";
    public static final String paternYYYY_MM_DD_Space_mm_hh_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String COMMANDE_DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";

    private DateUtil() {

    }

    public static boolean isFormatYYYYMMDDmmhhSS(String date) {
        return parseYYYYMMDDmmhhSS(date) != null;
    }

    public static java.util.Date parseCommandeStyle(String date) {
        return parse(date, COMMANDE_DATE_PATTERN);
    }

    public static String formatCommandeStyle(java.util.Date date) {
        return format(date, COMMANDE_DATE_PATTERN);
    }

    public static java.util.Date parseYYYYMMDDmmhhSS(String date) {
        return parse(date, paternYYYYMMDD);
    }

    public static String formatYYYYMMDDmmhhSS(java.util.Date date) {
        return format(date, paternYYYYMMDD);
    }

    public static String formatYYYY_MM_DD_Space_mm_hh_SS(java.util.Date date) {
        return format(date, paternYYYY_MM_DD_Space_mm_hh_SS);
    }

    public static java.util.Date now() {
        return new java.util.Date();
    }

    public static java.util.Date parse(String date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        java.util.Date myParsedDate = null;
        try {
            myParsedDate = formatter.parse(date);
        } catch (ParseException e) {
        }
        return myParsedDate;
    }

    public static String format(java.util.Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.format(date);
        }
        return null;
    }

    public static java.sql.Date convertToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static int[] nextMois(int mois, int annee) {
        if (mois == 0 && annee == 0) {
            mois = new Date().getMonth() + 1;
            annee = new Date().getYear() + 1900;// date par defaut mdekalyia 3ndhiom b 1 f les mois o 1900 f les annee dakechi dyial le pape gregore :p
            return new int[]{mois, annee};
        }
        if (mois < 12) {
            return new int[]{++mois, annee};
        } else {
            return new int[]{1, ++annee};
        }
    }

    public static Date calculerDateByTrimAndAnnee(int trimestriel, int annee) {
        String dateAsString = null;
        if (trimestriel == 1) {
            dateAsString = annee + "-03-31";
        } else if (trimestriel == 2) {
            dateAsString = annee + "-06-30";
        } else if (trimestriel == 3) {
            dateAsString = annee + "-09-30";
        } else if (trimestriel == 4) {
            dateAsString = annee + "-12-31";
        }
        return parse(dateAsString,paternYYYYMMDD);
    }

    public static java.sql.Date getSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.sql.Timestamp convertFromDateToTimestamp(java.util.Date date) {
        return new java.sql.Timestamp(date.getTime());
    }

    public static java.sql.Timestamp getSqlDateTime(java.util.Date date) {
        return new java.sql.Timestamp(date.getTime());
    }

    public static String getYearOfCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return simpleDateFormat.format(new Date());
    }

    public static String formateDate(String pattern, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static int calculDiff(Date date1, Date date2) {
        if (date1 == null) {
            return -1;
        }
        String datePaiementAsString = formatYYYYMMDDmmhhSS(date1);
        System.out.println("datePaiementAsString = " + datePaiementAsString);
        String dateAsString = formatYYYYMMDDmmhhSS(date2);
        System.out.println("datePresentationAsString = " + dateAsString);
        long monthsBetween = ChronoUnit.MONTHS.between(
                LocalDate.parse(datePaiementAsString).withDayOfMonth(1),
                LocalDate.parse(dateAsString).withDayOfMonth(1));
        int m = (int) -monthsBetween;
        System.out.println("monthsBetween = " + m);
        return m;
    }

}
