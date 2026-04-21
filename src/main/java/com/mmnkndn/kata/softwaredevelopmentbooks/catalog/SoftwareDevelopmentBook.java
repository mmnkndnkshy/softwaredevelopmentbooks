package com.mmnkndn.kata.softwaredevelopmentbooks.catalog;

public enum SoftwareDevelopmentBook {

    CLEAN_CODE(1, "Clean Code", "Robert Martin", 2008, 50.00),
    THE_CLEAN_CODER(2, "The Clean Coder", "Robert Martin", 2011, 50.00),
    CLEAN_ARCHITECTURE(3, "Clean Architecture", "Robert Martin", 2017, 50.00),
    TEST_DRIVEN_DEVELOPMENT(4, "Test-Driven Development By Example", "Kent Beck", 2003, 50.00),
    WORKING_WITH_LEGACY_CODE(5, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50.00);

    private final int id;
    private final String title;
    private final String author;
    private final int year;
    private final double price;

    SoftwareDevelopmentBook(int id, String title, String author, int year, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }
}