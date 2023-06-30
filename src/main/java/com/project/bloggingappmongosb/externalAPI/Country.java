package com.project.bloggingappmongosb.externalAPI;

import java.util.List;
import java.util.Map;

public class Country {
    private Name name;
    private List<String> tld;
    private String cca2;
    private String ccn3;
    private String cca3;
    private String cioc;
    private boolean independent;
    private String status;
    private boolean unMember;
    private Map<String, Currency> currencies;
    private Idd idd;
    private List<String> capital;
    private List<String> altSpellings;
    private String region;
    private String subregion;
    private Map<String, String> languages;
    private Translations translations;
    private List<Double> latlng;
    private boolean landlocked;
    private List<String> borders;
    private double area;
    private Demonyms demonyms;
    private String flag;
    private Map<String, String> maps;
    private int population;
    private Gini gini;
    private String fifa;
    private Car car;
    private List<String> timezones;
    private List<String> continents;
    private Flags flags;
    private CoatOfArms coatOfArms;
    private String startOfWeek;
    private CapitalInfo capitalInfo;
    private PostalCode postalCode;

}

class Name {
    private String common;
    private String official;
    private Map<String, String> nativeName;

}

class Currency {
    private String name;
    private String symbol;


}

class Idd {
    private String root;
    private List<String> suffixes;

}

class Translations {
    private Map<String, String> ara;
    private Map<String, String> bre;
    private Map<String, String> ces;
    private Map<String, String> cym;
}

class Demonyms {
    private Map<String, String> eng;
    private Map<String, String> fra;

}

class Gini {
    private double _2010;

}

class Car {
    private List<String> signs;
    private String side;

}

class Flags {
    private String png;
    private String svg;
    private String alt;

}

class CoatOfArms {
    private String png;
    private String svg;

}

class CapitalInfo {
    private List<Double> latlng;

}

class PostalCode {
    private String format;
    private String regex;
}

