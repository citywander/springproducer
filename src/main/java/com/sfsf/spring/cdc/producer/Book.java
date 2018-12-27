package com.sfsf.spring.cdc.producer;

public class Book {
    
    private Long id;
    
    private String title;
    
    private Integer price;

    public Book(Long id, String title, Integer price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
}
