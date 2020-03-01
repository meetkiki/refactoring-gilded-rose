package com.gildedrose;

public class Product {

    public String name;

    public int sellIn;

    public int quality;

    public Product(String name, int sell_in, int quality) {
        this.name = name;
        this.sellIn = sell_in;
        this.quality = quality;
    }

    public boolean nameEquals(String name){
        return this.name.equals(name);
    }

    public boolean nameNotEquals(String name){
        return !this.name.equals(name);
    }

    public Product autoIncrementQualityLessThanFifty() {
        if (this.quality < 50) {
            this.quality = this.quality + 1;
        }
        return this;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
